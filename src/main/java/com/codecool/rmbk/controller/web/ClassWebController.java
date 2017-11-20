package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLClass;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public class ClassWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLClass sqlClass = new SQLClass();
    String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        String accessLevel = validateRequest();
        String name = getLoggedUser().getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser());

        handleWebClass(accessLevel, name, sideMenu);
    }

    private void handleWebClass(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals("student")) {
            response = webDisplay.getSiteContent(name, sideMenu, prepareStudentOptions("class"));
            send200(response);

        } else if (accessLevel.equals("mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu, prepareMentorOptions("class"));
            send200(response);

        } else if (accessLevel.equals("admin")) {
            send403();
        }
    }

}