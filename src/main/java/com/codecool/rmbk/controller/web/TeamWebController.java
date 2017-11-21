package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLTeam;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TeamWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {
      
        setHttpExchange(httpExchange);

        String accessLevel = validateRequest();
        String name = getLoggedUser().getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser());

        handleWebTeam(accessLevel, name, sideMenu);
    }

    private void handleWebTeam(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals("Student")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    prepareStudentOptions("teams"),
                    null);
            send200(response);

        } else if (accessLevel.equals("Mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    prepareMentorOptions("teams"),
                    null);
            send200(response);

        } else if (accessLevel.equals("Admin")) {
            send403();
        }
    }

}
