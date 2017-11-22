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
    private Map<String, String> mainMenu;
    private Map<String, String> request;
    private String accessLevel;
    private String name;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        prepareController(httpExchange);
        request = parseURIstring(getRequestURI());
        handleAccesRights();

    }

    private void prepareController(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);
        accessLevel = validateRequest();
    }

    private void handleAccesRights() throws IOException {

        name = user.getFirstName();
        mainMenu = sqlMenuDAO.getSideMenu(user);

        switch (accessLevel) {
            case "Mentor":
                handleMentorArtifacts();
                break;
            case "Student":
                handleStudentArtifacts();
                break;
            case "Admin":
                send403();
        }
    }

    private void handleMentorArtifacts() throws IOException {

        String object = request.get("object");
        String action = request.get("action");
        String subject = request.get("subject");

        if (object == null) {
            viewArtifactTemplates();
        } else if (object.equals("new")) {
            addArtifactTemplate();
        } else {
            if (action == null) {
                showArtifactTemplate(object);
            } else if (action.equals("remove")) {
                removeArtifactTemplate(object);
            } else if (action.equals("edit")) {
                editArtifactTemplate(object);
            }
        }
        send200(response);
    }

    private void handleStudentArtifacts() throws IOException {

        String object = request.get("object");
        String action = request.get("action");
        String subject = request.get("subject");

        if (object == null) {
            viewArtifacts();
        } else if (object.equals("buy")) {
            buyArtifact();
        } else {
            if (action == null) {
                showArtifact(object);
            }
        }
        send200(response);
    }

    private void viewArtifactTemplates() {
        String[] options = {"Add"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> mainData = sqlArtifactTemplate.getArtifactTemplatesMap();

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, mainData, urlList);
    }

    private void viewArtifacts() {
        String[] options = {"Buy"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> mainData = sqlArtifact.getArtifactMapBy(user);

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, mainData, urlList);
    }

    private void buyArtifact() {

        if (httpExchange.getRequestMethod().equals("GET")) {
            Map<String, String> mainData = sqlArtifactTemplate.getArtifactTemplatesMap();

            response = webDisplay.getSiteContent(name, mainMenu, null , mainData,
                    "templates/buyable.twig");
        }
        if (httpExchange.getRequestMethod().equals("POST")) {
            try {
                Map<String, String> formData = getPostData();
                System.out.println(formData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addArtifactTemplate() {

        Map<String, String> labels = sqlArtifactTemplate.getArtifactLabels();

        response = webDisplay.getSiteContent(name, mainMenu, null, labels, urlEdit);

    }

    private void showArtifactTemplate(String object) {
        String[] options = {"Edit", "Remove"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> mainData = sqlArtifactTemplate.getArtifactInfo(object);

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, mainData, urlItem);
    }

    private void showArtifact(String object) {
        Map<String, String> mainData = sqlArtifact.getArtifactInfo(object);

        response = webDisplay.getSiteContent(name, mainMenu, null, mainData, urlItem);
    }

    private void removeArtifactTemplate(String object) throws IOException{

        sqlArtifactTemplate.removeArtifactTemplate(object);
        send302("/artifacts/");
    }

    private void editArtifactTemplate(String object) throws IOException{

        Map<String, String> labels = sqlArtifactTemplate.getArtifactLabels();

        response = webDisplay.getSiteContent(name, mainMenu, null, labels, urlEdit);
    }

    private Map<String, String> getPostData() throws IOException {

        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(),
                "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String formData = br.readLine();

        Map<String, String> inputs = parseFormData(formData);

        return inputs;
    }
}