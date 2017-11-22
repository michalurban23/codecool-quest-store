package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLQuest;
import com.codecool.rmbk.dao.SQLQuestTemplate;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class QuestWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLQuest sqlQuest = new SQLQuest();
    private SQLQuestTemplate sqlQuestTemplate = new SQLQuestTemplate();
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

        setHttpExchange(httpExchange);
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
        String subject = request.get("subject");

        if(object == null) {
            showAll();
        } else if (object.equals("new")) {
            addQuestTemplate();  // TODO
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

    private void addQuestTemplate() {

        Map<String, String> labels = sqlQuestTemplate.getTemplateLabels();

        response = webDisplay.getSiteContent(name, mainMenu, null, labels, urlEdit);
    }

    private void removeTemplate(String object) throws IOException {

        sqlQuestTemplate.removeQuestTemplate(object);
        send302("/quests/");
    }

    private void editTemplate(String object) {

        Map<String, String> labels = sqlQuestTemplate.getTemplateLabels();

        response = webDisplay.getSiteContent(name, mainMenu, null, labels, urlEdit);
    }

    private void handleStudentQuest() throws IOException {

        ;
    }

}
