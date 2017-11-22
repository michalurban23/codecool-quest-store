package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ArtifactWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLArtifact sqlArtifact = new SQLArtifact();
    private SQLArtifactTemplate sqlArtifactTemplate = new SQLArtifactTemplate();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);
        String accessLevel = validateRequest();
        validateAccessLevel(accessLevel);

    }

    private void validateAccessLevel(String accessLevel) throws IOException {

        switch (accessLevel) {
            case "Mentor":
                validateURIMentor();
                send200(response);
                break;
            case "Student":
                validateURIStudent();
                send200(response);
                break;
            case "Admin":
                send403();
        }
    }

    private void prepareMentorResponse() {

        String name = user.getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);
        String[] options = new String[]{"Add"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> mainData = sqlArtifactTemplate.getArtifactTemplatesMap();
        String URL = "templates/list_content.twig";

        response = webDisplay.getSiteContent(name, sideMenu, contextMenu, mainData, URL);
    }

    private void prepareStudentResponse() {

        String name = user.getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);
        String[] options = new String[]{"Buy"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> mainData = sqlArtifact.getArtifactMapBy(user);
        String URL = "templates/list_content.twig";

        response = webDisplay.getSiteContent(name, sideMenu, contextMenu, mainData, URL);
    }

    private void prepareRespone(String[] options, Map<String, String> mainData,
                                String URL) {

        String name = user.getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);
        Map<String, String> contextMenu = prepareContextMenu(options);

        response = webDisplay.getSiteContent(name, sideMenu, contextMenu, mainData, URL);
    }

    private void validateURIMentor() {

        Map<String, String> URI = parseURIstring(getRequestURI());

        if (URI.get("controller").equals("artifacts")) {
            prepareMentorResponse();
        } else if (URI.get("object").equals("new")) {
            prepareRespone(null, null, "templates/new.twig" );
        }
    }

    private void validateURIStudent() {


    }

    private void handleBuyArtifact() {

    }

    private void handleAddArtifact() {

    }

    private void handleSingleArtifact(String URI) {


    }

    private Map<String, String> readArtifactTemplateData(HttpExchange httpExchange) throws IOException {

        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(),
                "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String formData = br.readLine();

        return parseFormData(formData);
    }
}