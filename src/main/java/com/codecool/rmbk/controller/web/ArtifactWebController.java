package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class ArtifactWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException{

        String URL = validateUser();
        String response = WebDisplay.getSiteContent(user.getName(), sqlMenuDAO.getSideMenu(user),
                null, URL);
        send200(httpExchange, response);
    }

    private String validateUser() {

        String URL = null;

        if (user.getStatus().equals("Mentor")) {
            URL = "templates/mentor_artifacts.twig";
        } else if (user.getStatus().equals("Student")) {
            URL = "templates/student_artifacts.twig";
        }

        return URL;
    }
}
