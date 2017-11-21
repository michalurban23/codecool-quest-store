package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArtifactWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLArtifact sqlArtifact = new SQLArtifact();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);

        String accessLevel = validateRequest();

        String name = user.getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);

        handleWebArtifact(accessLevel, name, sideMenu);

    }

    private void handleWebArtifact(String accessLevel, String name,
                                   Map<String, String> sideMenu) throws IOException {

        Map<String, String> studentOptions = prepareStudentContextMenu();
        Map<String, String> mentorOptions = prepareMentorContextMenu();

        if (accessLevel.equals("Student")) {
            response = webDisplay.getSiteContent(name, sideMenu,studentOptions, sqlArtifact.getArtifactMapBy(user),
                    "templates/list_content.twig");

            send200(response);

        } else if (accessLevel.equals("Mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    mentorOptions, new HashMap<>(), "templates/list_content.twig");
            send200(response);

        } else if (accessLevel.equals("Admin")) {
            send403();
        }
    }

    private Map<String, String> prepareStudentContextMenu() {

        Map<String, String> options = new HashMap<>();
        options.put("Buy", prepareURI("artifacts","buy"));

        return options;
    }

    private Map<String, String> prepareMentorContextMenu() {

        Map<String, String> options = new HashMap<>();
        options.put("Add", prepareURI("artifacts","add"));

        return options;
    }
}