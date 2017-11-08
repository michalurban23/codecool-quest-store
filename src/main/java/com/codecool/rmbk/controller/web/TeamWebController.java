package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class TeamWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String URL = validateUser();

        String response = WebDisplay.getSiteContent("Team", null,
                null, URL);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String validateUser() {

        String URL = null;

        if (user.getStatus().equals("Mentor")) {
            URL = "templates/mentor_teams.twig";
        } else if (user.getStatus().equals("Student")) {
            URL = "templates/student_teams.twig";
        }

        return URL;
    }


}
