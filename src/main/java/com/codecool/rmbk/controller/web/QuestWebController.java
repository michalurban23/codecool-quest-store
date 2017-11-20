package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLQuest;
import com.codecool.rmbk.dao.SQLQuestTemplate;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class QuestWebController extends CommonHandler {

    SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();

    public void handle(HttpExchange httpExchange) throws IOException {

        String response;
        String accessLevel = validateRequest(httpExchange);
        String name = getLoggedUser(httpExchange).getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser(httpExchange));

        if (accessLevel.equals("student")) {
            response = webDisplay.getSiteContent(name, sideMenu, new HashMap<>());
            send200(httpExchange, response);

        } else if (accessLevel.equals("mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu, new HashMap<>());
            send200(httpExchange, response);

        } else if (accessLevel.equals("admin")) {
            send403(httpExchange);
        }
    }

}
