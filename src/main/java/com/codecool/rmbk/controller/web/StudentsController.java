package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public class StudentsController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLUsers sqlUsers = new SQLUsers();
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);

        String accessLevel = validateRequest();
        String name = user.getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);

        handleWebStudents(accessLevel, name, sideMenu);
    }

    private void handleWebStudents(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals("Student")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    null,
                    null, "templates/list_content.twig");
            send200(response);

        } else if (accessLevel.equals("Mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    null,
                    sqlUsers.getUserMap("student"), "templates/list_content.twig");
            send200(response);

        } else if (accessLevel.equals("Admin")) {
            send403();
        }
    }

}