package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.*;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BacklogWebController extends CommonHandler {

    private SQLBacklog backlogDao = new SQLBacklog();
    private SQLExperience experienceDao = new SQLExperience();
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

        name = loggedUser.getFirstName();

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

        Map <String, String> backlog = backlogDao.getBacklogMap(loggedUser);

        String totalCoinsEver = backlogDao.getExperience(loggedUser.getID());
        String coinsAvailable = backlogDao.getCurrentCoins(loggedUser.getID()).toString();
        String levelName = experienceDao.getExperienceInfo(totalCoinsEver);
        String missing = experienceDao.getMissingExp(totalCoinsEver);

        Map<String, String> summary = new LinkedHashMap<>();
        summary.put("Available coins: ", coinsAvailable);
        summary.put("Totally ever earned coins: ", totalCoinsEver);
        summary.put("Actual experience level: ", levelName);
        summary.put("Coins remaining to get next level: ", missing);

        response = webDisplay.getSiteContentMap(name, mainMenu, null, summary, backlog, "templates/backlog.twig");
    }

    private void showTemplate(String object) {

        Map <String, String> activity = backlogDao.getBacklogDetail(object);

        response = webDisplay.getSiteContent(name, mainMenu, null, activity, urlItem);
    }

    private void handleMentorBacklog() throws IOException {

        String object = request.get("object");

        if (object == null) {
            showAllBacklogs();
        } else {
            showTemplate(object);
        }
        send200(response);
    }

    private void showAllBacklogs() {

        Map <String, String> backlogs = backlogDao.getAllBacklogs();

        response = webDisplay.getSiteContent(name, mainMenu, null, backlogs, urlJustList);
    }

}