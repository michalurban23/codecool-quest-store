package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.sun.net.httpserver.HttpExchange;

import com.codecool.rmbk.view.WebDisplay;

import java.io.IOException;

public class ClassWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String URL = validateUser(httpExchange);
        String response = WebDisplay.getSiteContent(getLoggedUser(httpExchange).getFirstName(),
                sqlMenuDAO.getSideMenu(getLoggedUser(httpExchange)), null, URL);
        send200(httpExchange, response);

    }

    private String validateUser(HttpExchange httpExchange) {

        String URL = null;

        if (getLoggedUser(httpExchange).getFirstName().equals("Mentor")) {
            URL = "templates/mentor_teams.twig";
        } else if (getLoggedUser(httpExchange).getFirstName().equals("Student")) {
            URL = "templates/student_teams.twig";
        }

        return URL;
    }
}