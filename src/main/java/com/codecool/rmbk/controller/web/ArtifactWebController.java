package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.dao.SQLBacklog;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.helper.StringParser;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ArtifactWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLArtifact sqlArtifact = new SQLArtifact();
    private SQLArtifactTemplate sqlArtifactTemplate = new SQLArtifactTemplate();
    private SQLBacklog sqlBacklog= new SQLBacklog();
    private Map<String, String> mainMenu;
    private Map<String, String> request;
    private List<String> templateData;
    private String accessLevel;
    private String name;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        prepareController(httpExchange);
        request = parseURIstring(getRequestURI());
        handleAccessRights();

    }

    private void prepareController(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);
        accessLevel = validateRequest();
    }

    private void handleAccessRights() throws IOException {

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
        } else if (object.equals("new")) {
            buyArtifact(object);
        } else {
            if (action == null) {
                showBuyableArtifact(object);
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
        String[] options = {"Acquire"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> mainData = sqlArtifact.getArtifactMapBy(user);

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, mainData, urlList);
    }

    private void buyArtifact(String object) throws IOException{

        String method = httpExchange.getRequestMethod();
        Map<String, String> templates = sqlArtifactTemplate.getArtifactTemplatesMap();

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null , templates,
                    "templates/buyable.twig");
        } else if (method.equals("POST")) {
            readBuyableArtifactsInputs();
            if (checkIfBuyable()) {
                buyArtifacts();
                send302("/artifacts/");
            } else {
                getFailureResponse();
            }
        }
    }

    private void addArtifactTemplate() throws IOException {

        String method = httpExchange.getRequestMethod();
        String title = "Create new Artifact template: ";
        Map<String, String> labels = sqlArtifactTemplate.getArtifactLabels();


        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null, title, labels, urlEdit);
        } else if (method.equals("POST")) {
            readArtifactTemplateInputs();
            sqlArtifactTemplate.addArtifactTemplate(templateData);
            send302("/artifacts/");
        }
    }

    private void showArtifactTemplate(String object) {
        String[] options = {"Edit", "Remove"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> mainData = sqlArtifactTemplate.getArtifactInfo(object);

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, mainData, urlItem);
    }

    private void showBuyableArtifact(String object) {
        Map<String, String> mainData = sqlArtifactTemplate.getArtifactInfo(object);

        response = webDisplay.getSiteContent(name, mainMenu, null, mainData, urlItem);
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

        String method = httpExchange.getRequestMethod();
        String title = "Editing " + StringParser.addWhitespaces(object) + ":";
        Map<String, String> labels = sqlArtifactTemplate.getArtifactInfo(object);

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null, title, labels, urlEdit);
        } else if (method.equals("POST")) {
            readArtifactTemplateInputs();
            sqlArtifactTemplate.editArtifactTemplate(object, templateData);
            send302("/artifacts/");
        }

    }

    private Boolean checkIfBuyable() {

        Boolean buyable;
        Integer coins = sqlBacklog.getCurrentCoins(user.getID());
        Integer value = calculateArtifactsValue();

        buyable = coins > value ;

        return buyable;
    }

    private Integer calculateArtifactsValue() {

        Integer value = 0;

        for(String templateName : templateData) {
            value += sqlArtifactTemplate.getTemplateValue(templateName);
        }

        return value;
    }

    private void readArtifactTemplateInputs() throws IOException{

        Map<String, String> inputs = readInputs();
        templateData = new ArrayList<>();

        templateData.add(inputs.get("name"));
        templateData.add(inputs.get("description"));
        templateData.add(inputs.get("value"));
        templateData.add(inputs.get("special"));
        templateData.add(inputs.get("active"));
    }

    private void readBuyableArtifactsInputs() throws IOException {

        Map<String, String> inputs = readInputs();
        templateData = new ArrayList<>();

        for (String templateName : inputs.keySet()) {
            templateData.add(inputs.get(templateName));
        }
    }

    private void buyArtifacts() {
        String owner = String.valueOf(user.getID());

        for (String templateName : templateData) {
            sqlArtifact.addArtifact(templateName, owner);
        }
    }

    private void getFailureResponse() {

        response = "Ni mosz hajsu";
    }
}