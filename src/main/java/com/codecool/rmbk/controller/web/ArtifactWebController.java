package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.view.WebDisplay;
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

    private void handleWebArtifact(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals("Student")) {
            response = webDisplay.getSiteContent(name, sideMenu, prepareMentorArtifactOptions());
            send200(response);

        } else if (accessLevel.equals("Mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu, prepareMentorArtifactOptions());
            send200(response);

        } else if (accessLevel.equals("Admin")) {
            send403();
        }
    }

    private Map<String, String> prepareMentorArtifactOptions() {

        Map<String, String> options = new HashMap<>();
        options.put("Display", "/artifacts/display");
        options.put("Edit", "/artifacts/edit");
        options.put("Add", "/artifacts/add");
        options.put("Delete", "/artifacts/delete");

        return options;
    }

}