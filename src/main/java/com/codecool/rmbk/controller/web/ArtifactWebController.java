package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
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

    private void validateURIMentor() {

        switch (getRequestURI()) {
            case "artifacts/add":
                handleAddArtifact();
            default:
                prepareMentorResponse();
        }
    }

    private void validateURIStudent() {

        switch (getRequestURI()) {
            case "artifacts/buy":
                handleBuyArtifact();
            default:
                prepareStudentResponse();
        }
    }

    private void handleBuyArtifact() {

    }

    private void handleAddArtifact() {

    }
}