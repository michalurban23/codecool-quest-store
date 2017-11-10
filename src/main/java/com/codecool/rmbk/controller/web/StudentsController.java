package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.sun.net.httpserver.HttpExchange;

import com.codecool.rmbk.view.WebDisplay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentsController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLUsers sqlUsers = new SQLUsers();

    public void handle(HttpExchange httpExchange) throws IOException {

        String response;
        String accessLevel = validateRequest(httpExchange);
        String name = getLoggedUser(httpExchange).getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser(httpExchange));
        Map<String, String> data = sqlUsers.getUserMap("student");

        if (accessLevel.equals("student")) {
            send403(httpExchange);

        } else if (accessLevel.equals("mentor")) {
            String URL = "templates/students.twig";
            response = WebDisplay.getSiteContent(name, sideMenu, data, URL);
            send200(httpExchange, response);

        } else if (accessLevel.equals("admin")) {
            send403(httpExchange);
        }
    }
}