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
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);

        String accessLevel = validateRequest();
        String name = user.getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);

        handleWebMentor(accessLevel, name, sideMenu);
    }


    private void handleWebMentor(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals(sideMenu)) {
            send403();
        } else if (accessLevel.equals("Mentor")) {
            send403();
        } else if (accessLevel.equals("Admin")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    null, sqlUsers.getUserMap("mentor"), "templates/list_content.twig");

            send200(response);
        }
    }
}