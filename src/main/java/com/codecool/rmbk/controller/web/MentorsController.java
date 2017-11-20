package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MentorsController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLUsers sqlUsers = new SQLUsers();


    public void handle(HttpExchange httpExchange) throws IOException {

        String response;
        String accessLevel = validateRequest();
        String name = getLoggedUser().getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser());
        Map<String, String> data = sqlUsers.getUserMap("mentor");

        if (accessLevel.equals("student")) {
            send403();

        } else if (accessLevel.equals("mentor")) {
            send403();

        } else if (accessLevel.equals("admin")) {
            String URL = "templates/mentors.twig";
            response = webDisplay.getSiteContent(name, sideMenu, data);
            send200(response);
        }
    }
}