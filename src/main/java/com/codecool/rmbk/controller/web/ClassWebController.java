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

        setHttpExchange(httpExchange);

        String accessLevel = validateRequest();
        String name = user.getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);

        handleWebClass(accessLevel, name, sideMenu);
    }

    private void handleWebClass(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals("Student")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    prepareStudentOptions("classes"), null);
            send200(response);

        } else if (accessLevel.equals("Mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    prepareMentorOptions("class"), null);
            send200(response);

        } else if (accessLevel.equals("Admin")) {
            send403();

        }
    }

}