package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLClass;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.sun.net.httpserver.HttpExchange;

import com.codecool.rmbk.view.WebDisplay;

import java.io.IOException;
import java.util.Map;

public class ClassWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLClass sqlClass = new SQLClass();

    public void handle(HttpExchange httpExchange) throws IOException {

        String response;
        String accessLevel = validateRequest(httpExchange);
        String name = getLoggedUser(httpExchange).getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser(httpExchange));
        Map<String, String> data = sqlClass.getGroupMap(getLoggedUser(httpExchange));

        if (accessLevel.equals("student")) {
            send403(httpExchange);

        } else if (accessLevel.equals("mentor")) {
            String URL = "templates/mentor_groups.twig";
            response = webDisplay.getSiteContent(name, sideMenu, data);
            send200(httpExchange, response);

        } else if (accessLevel.equals("admin")) {
            String URL = "templates/admin_classes.twig";
            response = webDisplay.getSiteContent(name, sideMenu, data);
            send200(httpExchange, response);
        }
    }
}