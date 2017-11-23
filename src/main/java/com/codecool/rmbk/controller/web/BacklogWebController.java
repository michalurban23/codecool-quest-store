package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLBacklog;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLQuest;
import com.codecool.rmbk.dao.SQLQuestTemplate;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BacklogWebController extends CommonHandler {

    private SQLBacklog backlogDao = new SQLBacklog();
    private Map<String, String> request;
    private String accessLevel;
    private String name;
    private String urlJustList = "templates/list.twig";

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

        switch (accessLevel) {
            case "Student":
                handleStudentBacklog();
                break;
            case "Mentor":
                handleMentorBacklog();
                break;
            default:
                send403();
                break;
        }
    }

    private void handleStudentBacklog() throws IOException {

        String object = request.get("object");

        if (object == null) {
            showAll();
        } else {
            showTemplate(object);
        }
        send200(response);
    }

    private void showAll() {

        Map <String, String> backlog = backlogDao.getBacklogMap(user);
        String[] summary = {String.format("You have a total of %s", 2),
                "qweqweq",
                "rqwrq"}; // TODO

        response = webDisplay.getSiteContent(name, mainMenu, null, summary, backlog, urlJustList);
    }

    private void showTemplate(String object) {

        Map <String, String> activity = backlogDao.getBacklogDetail(object);

        response = webDisplay.getSiteContent(name, mainMenu, null, activity, urlItem);
    }

    private void handleMentorBacklog() {

        // TODO
    }

}