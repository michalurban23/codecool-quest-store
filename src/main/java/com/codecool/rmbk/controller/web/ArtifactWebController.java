package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArtifactWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String response;
        String accessLevel = validateRequest(httpExchange);
        String name = getLoggedUser(httpExchange).getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser(httpExchange));

        if (accessLevel.equals("student")) {
            String URL = "templates/student_artifacts.twig";
            response = WebDisplay.getSiteContent(name, sideMenu, new HashMap<>(), URL);
            send200(httpExchange, response);

        } else if (accessLevel.equals("mentor")) {
            String URL = "templates/mentor_artifacts.twig";
            response = WebDisplay.getSiteContent(name, sideMenu, new HashMap<>(), URL);
            send200(httpExchange, response);

        } else if (accessLevel.equals("admin")) {
            send403(httpExchange);

        }
    }
}