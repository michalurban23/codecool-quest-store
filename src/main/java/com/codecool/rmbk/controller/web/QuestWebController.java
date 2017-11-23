package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLQuest;
import com.codecool.rmbk.dao.SQLQuestTemplate;
import com.codecool.rmbk.helper.StringParser;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLQuest sqlQuest = new SQLQuest();
    private SQLQuestTemplate sqlQuestTemplate = new SQLQuestTemplate();
    private List<String> templateData;
    private Map<String, String> mainMenu;
    private Map<String, String> request;
    private String accessLevel;
    private String name;

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
            case "Student":
                handleStudentQuest();
                break;
            case "Mentor":
                handleMentorQuest();
                break;
            default:
                send403();
                break;
        }
    }

    private void handleMentorQuest() throws IOException {

        String object = request.get("object");
        String action = request.get("action");

        if(object == null) {
            showAll();
        } else if (object.equals("new")) {
            addQuestTemplate();
        } else {
            if (action == null) {
                showTemplate(object);
            } else if (action.equals("remove")) {
                removeTemplate(object);
            } else if (action.equals("edit")) {
                editTemplate(object);
            }
        }
        send200(response);
    }

    private void showAll() {

        String[] options = {"Add"};
        Map <String, String> contextMenu = prepareContextMenu(options);
        Map <String, String> allQuests = sqlQuestTemplate.getTemplatesMap();

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, allQuests, urlList);
    }

    private void showTemplate(String object) {

        String[] options = {"Edit", "Remove"};

        Map <String, String> contextMenu = prepareContextMenu(options);
        Map <String, String> allQuests = sqlQuestTemplate.getTemplateInfo(object);

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, allQuests, urlItem);
    }

    private void addQuestTemplate() throws IOException {

        String method = httpExchange.getRequestMethod();
        String title = "Create New Template:";
        List<String> labels = sqlQuestTemplate.getTemplateLabels();

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null, title, labels, urlAdd);
        } else if (method.equals("POST")) {
            readQuestInputs();
            Boolean properData = verifyInputs();
            if (properData) {
                sqlQuestTemplate.addQuestTemplate(templateData);
                send302("/quests/");
            } else {
                showFailureMessage();
            }
        }
    }

    private void removeTemplate(String object) throws IOException {

        sqlQuestTemplate.removeQuestTemplate(object);
        send302("/quests/");
    }

    private void editTemplate(String object) throws IOException {

        String method = httpExchange.getRequestMethod();
        String title = "Editing " + StringParser.addWhitespaces(object) + ":";
        Map<String, String> labels = sqlQuestTemplate.getTemplateInfo(object);

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null, title, labels, urlEdit);
        } else if (method.equals("POST")) {
            readQuestInputs();
            Boolean properData = verifyInputs();
            if (properData) {
                sqlQuestTemplate.editQuestTemplate(templateData);
                send302("/quests/");
            } else {
                showFailureMessage();
            }
        }
    }

    private void readQuestInputs() throws IOException {

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

    private void showFailureMessage() {

        response = "FAIL"; // TODO
    }

    private void handleStudentQuest() throws IOException {

        // TODO
    }

}
