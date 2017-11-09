package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class GroupWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String URL = validateUser(httpExchange);

        String response = WebDisplay.getSiteContent("a", null,
                null, URL);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String validateUser(HttpExchange httpExchange) {

        String URL = null;

        if (getLoggedUser(httpExchange).getFirstName().equals("Mentor")) {
            URL = "templates/mentor_groups.twig";
        } else if (getLoggedUser(httpExchange).getFirstName().equals("Student")) {
            URL = "templates/student_groups.twig";
        }

        return URL;
    }

}
