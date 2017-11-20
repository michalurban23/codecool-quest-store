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

    public void handle(HttpExchange httpExchange) throws IOException {

        String response;
        String accessLevel = validateRequest();
        String name = getLoggedUser().getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser());

        if (accessLevel.equals("student")) {
            response = webDisplay.getSiteContent(name, sideMenu, new HashMap<>());
            send200(response);

        } else if (accessLevel.equals("mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu, new HashMap<>());
            send200(response);

        } else if (accessLevel.equals("admin")) {
            send403();
        }
    }

}
