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
        String accessLevel = validateRequest();
        String name = getLoggedUser().getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser());
        Map<String, String> data = sqlClass.getGroupMap(getLoggedUser());

        if (accessLevel.equals("student")) {
            send403();

        } else if (accessLevel.equals("mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu, data);
            send200(response);

        } else if (accessLevel.equals("admin")) {
            response = webDisplay.getSiteContent(name, sideMenu, data);
            send200(response);
        }
    }
}