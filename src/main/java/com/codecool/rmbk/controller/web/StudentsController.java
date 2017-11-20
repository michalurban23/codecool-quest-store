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
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);

        String accessLevel = validateRequest();
        String name = getLoggedUser().getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser());

        handleWebStudents(accessLevel, name, sideMenu);
    }

    private void handleWebStudents(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals("student")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    prepareStudentOptions("students"),
                    null);
            send200(response);

        } else if (accessLevel.equals("mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    prepareMentorOptions("students"),
                    sqlUsers.getUserMap("student"));
            send200(response);

        } else if (accessLevel.equals("admin")) {
            send403();
        }
    }

}