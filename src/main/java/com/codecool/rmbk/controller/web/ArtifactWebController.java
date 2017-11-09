package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class ArtifactWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException{

        String URL = getArtifactURL(httpExchange);

        String response = WebDisplay.getSiteContent(getLoggedUser(httpExchange).getFirstName(),
                sqlMenuDAO.getSideMenu(getLoggedUser(httpExchange)),
                null, URL);
        send200(httpExchange, response);


    }

    private String getArtifactURL(HttpExchange httpExchange) {

        String URL = null;

        if (getLoggedUser(httpExchange).getFirstName().equals("Mentor")) {
            URL = "templates/mentor_artifacts.twig";
        } else if (getLoggedUser(httpExchange).getFirstName().equals("Student")) {
            URL = "templates/student_artifacts.twig";
        }

        return URL;
    }


}