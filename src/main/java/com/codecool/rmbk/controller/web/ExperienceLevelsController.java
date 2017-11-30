package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLExperience;
import com.codecool.rmbk.helper.StringParser;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExperienceLevelsController extends CommonHandler {

    private SQLExperience expDao = new SQLExperience();
    private Map<String, String> request;
    private String accessLevel;
    private String name;
    private String urlExpSlider = "templates/slider.twig";
    private ArrayList<String> levelData;

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
            case "Admin":
                handleExperienceLevels();
                break;
            default:
                send403();
                break;
        }
    }

    private void handleExperienceLevels() throws IOException {

        String object = request.get("object");
        String action = request.get("action");

        if (object == null) {
            showCurrentLevels();
        } else if (object.equals("new")) {
            addLevel();
        } else {
            if (action == null) {
                showLevel(object);
            } else if (action.equals("remove")) {
                removeLevel(object);
            } else if (action.equals("edit")) {
                editLevel(object);
            }
        }
        send200(response);
    }

    private void showCurrentLevels() {

        String[] options = {"Add"};
        Map <String, String> contextMenu = prepareContextMenu(options);
        Map <String, String> levels = expDao.getLevelsMap();

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, levels, urlList);
    }

    private void addLevel() throws IOException {

        String method = httpExchange.getRequestMethod();
        String title = "Create New Template:";
        List<String> labels = Arrays.asList("Level Name", "Minimum Required Exp");

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null, title, labels, urlAdd);
        } else if (method.equals("POST")) {
            readLevelInputs();
            Boolean properData = verifyInputs();
            if (properData) {
                expDao.addNewLevel(levelData);
                send302("/experience/");
            } else {
                showFailureMessage();
            }
        }
    }

    private void showLevel(String object) {

        String[] options = {"Edit", "Remove"};

        Map <String, String> contextMenu = prepareContextMenu(options);
        Map <String, String> level = expDao.getLevelInfo(object);

        response = webDisplay.getSiteContent(name, mainMenu, contextMenu, level, urlItem);
    }

    private void editLevel(String object) throws IOException {

        String method = httpExchange.getRequestMethod();
        String title = "Editing " + StringParser.addWhitespaces(object) + ":";
        List<String> boundaries = expDao.getBoundaries(object);

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(name, mainMenu, null, title, boundaries, urlExpSlider);
        } else if (method.equals("POST")) {
            readLevelInputs();
            expDao.editLevels(object, levelData);
            send302("/experience/");
        }
    }

    private void removeLevel(String object) throws IOException {

        expDao.removeLevel(object);
        send302("/experience/");
    }

    private void readLevelInputs() throws IOException {

        Map<String, String> inputs = readInputs();
        levelData = new ArrayList<>();

        levelData.add(inputs.get("Level Name"));
        levelData.add(inputs.get("Minimum Required Exp"));
    }

    private Boolean verifyInputs() {

        try {
            Integer value = Integer.parseInt(levelData.get(1));

            return value > 0;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showFailureMessage() throws IOException {

        send302("/experience"); // TODO
    }

}
