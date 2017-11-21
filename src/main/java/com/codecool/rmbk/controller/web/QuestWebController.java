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
    SQLQuest sqlQuest = new SQLQuest();
    SQLQuestTemplate sqlQuestTemplate = new SQLQuestTemplate();
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);

        String accessLevel = validateRequest();
        String name = user.getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);

        handleWebQuest(accessLevel, name, sideMenu);
    }

    private void handleWebQuest(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals("Student")) {
            response = webDisplay.getSiteContent(name, sideMenu,null,
                    sqlQuest.getQuestMap(user.getID(), "rmbk"), "templates/list_content.twig");
            send200(response);

        } else if (accessLevel.equals("Mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    null,
                    null, "templates/list_content.twig");

            send200(response);

        } else if (accessLevel.equals("Admin")) {
            send403();
        }
    }

}
