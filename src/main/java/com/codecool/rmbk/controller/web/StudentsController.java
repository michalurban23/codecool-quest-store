package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.model.usr.User;
import com.sun.net.httpserver.HttpExchange;

import com.codecool.rmbk.view.WebDisplay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentsController extends UserController {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLUsers userDAO = new SQLUsers();
    private SQLUsers sqlUsers = new SQLUsers();
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);
        parseURIstring(getRequestURI());
        validateRequest();

        if (parsedURI.get("object") == null) {
            showList();
        } else if (parsedURI.get("action") == null){
            showDetails();
        } else {
            performAction();
        }
    }

    private void performAction() throws IOException {
        String action = parsedURI.get("action");
        User user = userDAO.getUserByID(Integer.parseInt(parsedURI.get("object")));
        switch (action) {
            case "add":
                User newUser = userDAO.addUser("Student");
                send302(String.format("/students/%s/edit", String.valueOf(newUser.getID())));
                break;
            case "edit":
                editUserData(user);
                break;
            case "remove":
                userDAO.removeUser(user);
                break;
        }
    }

    private void editUserData(User user) {
    }

    private void showDetails() throws IOException {
        if (user.getClass().getSimpleName().equals("Mentor")) {
            response = webDisplay.getSiteContent(user.getFullName(), sideMenu,
                    prepareContextMenu(getAllowedActions()),
                    sqlUsers.getUserMap("student"), "templates/list_content.twig");
            send200(response);
        } else {
            send403();
        }
    }

    private void showList() throws IOException {

        if (user.getClass().getSimpleName().equals("Mentor")) {

            response = webDisplay.getSiteContent(user.getFullName(), sideMenu,
                    prepareContextMenu(getAllowedActions()),
                    sqlUsers.getUserMap("student"), "templates/list_content.twig");
            send200(response);
        } else {
            send403();
        }
    }

}