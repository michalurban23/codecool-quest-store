package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.*;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
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

        String[] summary = {String.format("You have %s coins available", coinsAvailable),
                            String.format("Overall, you have earned a total of %s coins", totalCoinsEver),
                            String.format("That puts you at the experience level: %s", levelName),
                            String.format("You need to earn %s more coins for next level", missing)};

        response = webDisplay.getSiteContent(name, mainMenu, null, summary, backlog, urlJustList);
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