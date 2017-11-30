package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.dao.SQLBacklog;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.helper.StringParser;
import com.codecool.rmbk.model.item.Item;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private String urlListMentorArtifacts = "templates/list_mentor_quests.twig";
    private String urlListArtifacts = "templates/list_student_artifacts.twig";

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

        name = loggedUser.getFirstName();
        mainMenu = sqlMenuDAO.getSideMenu(loggedUser);

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

        if (object == null) {
            viewArtifactTemplates();
        } else if (object.equals("new")) {
            addArtifactTemplate();
        } else if (object.equals("accept")) {
            showArtifactsToAccept();
        } else {
            if (action == null) {
                showItem(object);
            } else if (action.equals("remove")) {
                removeArtifactTemplate(object);
            } else if (action.equals("edit")) {
                editArtifactTemplate(object);
            } else if (action.equals("accept")) {
                acceptArtifact(object);
                send302("/artifacts/accept");
            }
        }
        send200(response);
    }

    private void showItem(String object) {

        try {
            Integer.parseInt(object);
            showArtifact(object);
        } catch (NumberFormatException e) {
            showArtifactTemplate(object);
        }
    }

    private void handleStudentArtifacts() throws IOException {

        String object = request.get("object");
        String action = request.get("action");

        if(object == null) {
            showMyArtifacts();
        } else if (object.equals("new")) {
            acquireNewArtifact();
        } else if (object.equals("bought")) {
            showBought();
        } else {
            if (action == null) {
                showArtifactDetails(object);
            } else if (action.equals("use")) {
                buyArtifact(object);
                send302("/artifacts/");
            }
        }
        send200(response);
    }

    private void showMyArtifacts() {

        String title = "My bought artifacts";
        String[] options = {"Acquire", "Bought"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> myQuests = sqlArtifact.getArtifactMapBy(loggedUser);

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, title, myQuests, urlListArtifacts);
    }

    private void showBought() {

        String title = "My requested artifacts";
        String[] options = {"Acquire"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> boughtArtifacts = sqlArtifact.getBoughtArtifactsMapBy(loggedUser);

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, title, boughtArtifacts, urlJustList);
    }

    private void viewArtifactTemplates() {

        String[] options = {"Add", "Accept"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> mainData = sqlArtifactTemplate.getArtifactTemplatesMap();

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, mainData, urlList);
    }

    private void acquireNewArtifact() throws IOException {

        String method = httpExchange.getRequestMethod();
        Map<String, String> templates = sqlArtifactTemplate.getAvailableArtifacts(loggedUser);

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null , templates,
                    "templates/buyable.twig");
        } else if (method.equals("POST")) {
            readBuyableArtifactsInputs();
            if (checkIfBuyable()) {
                saveNewArtifacts();
                send302("/artifacts/");
            } else {
                getNoMoneyResponse();
            }
        }
    }

    private void addArtifactTemplate() throws IOException {

        String method = httpExchange.getRequestMethod();
        String title = "Create new Artifact template: ";
        List<String> labels = sqlArtifactTemplate.getArtifactLabels();

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null, title, labels, urlAdd);
        } else if (method.equals("POST")) {

            readArtifactTemplateInputs();
            Boolean properData = verifyInputs();
            if (properData) {
                sqlArtifactTemplate.addArtifactTemplate(templateData);
                send302("/artifacts/");
            } else {
                showFailureMessage();
            }
        }
    }

    private void editArtifactTemplate(String object) throws IOException{

        String method = httpExchange.getRequestMethod();
        String title = "Editing " + StringParser.addWhitespaces(object) + ":";
        Map<String, String> labels = sqlArtifactTemplate.getArtifactInfo(object);

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null, title, labels, urlEdit);
        } else if (method.equals("POST")) {
            readArtifactTemplateInputs();
            Boolean properData = verifyInputs();
            if (properData) {
                sqlArtifactTemplate.editArtifactTemplate(object, templateData);
                send302("/artifacts/");
            } else {
                showFailureMessage();
            }
        }
    }

    private void showArtifactsToAccept() {

        String title = "Accept artifact purchase:";
        Map<String, String> boughtArtifacts = sqlArtifact.getAllBoughtArtifactsMap();

        response = webDisplay.getSiteContent(name, mainMenu, null, title, boughtArtifacts, urlListMentorArtifacts);
    }

    private void acceptArtifact(String object) {

        Map<String, String> artifactInfo = sqlArtifact.getArtifactInfo(object);
        List<String> data = Arrays.asList(artifactInfo.get("template_name"), artifactInfo.get("value"));

        Item artifact = new Item(data, artifactInfo.get("owner"));
        sqlArtifact.acceptArtifact(artifact);
    }

    private void showArtifactTemplate(String object) {

        String[] options = {"Edit", "Remove"};
        Map<String, String> contextMenu = prepareContextMenu(options);
        Map<String, String> mainData = sqlArtifactTemplate.getArtifactInfo(object);

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, mainData, urlItem);
    }

    private void showArtifact(String object) {

        Map <String, String> artifactData = sqlArtifact.getArtifactInfo(object);

        response = webDisplay.getSiteContent(name, mainMenu, null, artifactData, urlItem);
    }

    private void removeArtifactTemplate(String object) throws IOException{

        sqlArtifactTemplate.removeArtifactTemplate(object);
        send302("/artifacts/");
    }

    private Boolean checkIfBuyable() {

        Integer coins = sqlBacklog.getCurrentCoins(loggedUser.getID());
        Integer value = calculateArtifactsValue();

        return coins > value;
    }

    private Integer calculateArtifactsValue() {

        Integer value = 0;

        for (int i = 0; i < templateData.size(); i += 2) {
            String templateName = templateData.get(i);
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

    private Boolean verifyInputs() {

        List<Integer> binaries = new ArrayList<>();
        binaries.add(0);
        binaries.add(1);

        try {
            Integer value = Integer.parseInt(templateData.get(2));
            Integer special = Integer.parseInt(templateData.get(3));
            Integer active = Integer.parseInt(templateData.get(4));

            return value > 0 && binaries.contains(special) && binaries.contains(active);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void readBuyableArtifactsInputs() throws IOException {

        Map<String, String> inputs = readInputs();
        templateData = new ArrayList<>();

        for (String templateName : inputs.keySet()) {
            templateData.add(templateName);
            templateData.add(inputs.get(templateName));
        }
    }

    private void saveNewArtifacts() {

        for (int i = 0 ; i < templateData.size() ; i += 2) {

            String questName = templateData.get(i);
            String questValue = templateData.get(i+1);

            List<String> questData = Arrays.asList(questName, questValue);
            Item artifact = new Item(questData, loggedUser);

            sqlArtifact.getNewArtifact(artifact);
        }
    }

    private void buyArtifact(String object) {

        Map<String, String> artifactInfo = sqlArtifact.getArtifactInfo(object);
        List<String> data = Arrays.asList(artifactInfo.get("template_name"), artifactInfo.get("value"));

        Item artifact = new Item(data, loggedUser);
        sqlArtifact.buyArtifact(artifact);
    }

    private void getNoMoneyResponse() throws IOException {

        send302("/static/poor.html");
    }

    private void showArtifactDetails(String object) {

        Map<String, String> artifact = sqlArtifact.getArtifactInfo(object);

        response = webDisplay.getSiteContent(name, mainMenu, null, artifact, urlItem);
    }

}