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
    private String accessLevel;
    private String request;
    private String name;

    public void handle(HttpExchange httpExchange) throws IOException {

        prepareController(httpExchange);
        request = getRequestURI();
        handleAccessRights();
    }

    private void prepareController(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);
        accessLevel = validateRequest();
    }

    private void handleAccessRights() throws IOException {

        name = user.getFirstName();
        mainMenu = sqlMenuDAO.getSideMenu(user);
        System.out.println(accessLevel);
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

        System.out.println(request);
        switch (request) {
            case "/quests/add":
                addQuest();
                break;
            default:
                showAll();
                break;
        }
        send200(response);
    }

    private void showAll() {

        String[] options = {"Add"};
        Map <String, String> contextMenu = prepareContextMenu(options);
        Map <String, String> allQuests = sqlQuestTemplate.getTemplatesMap();
        for (String s: allQuests.keySet()) {
            System.out.println(s);
            System.out.println(allQuests.get(s));
        }

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, allQuests, urlList);
    }

    private void addQuest() {

        response = webDisplay.getSiteContent(name, mainMenu, null, null, urlList);
    }

    private void handleStudentQuest() throws IOException {

        ;
    }

}
