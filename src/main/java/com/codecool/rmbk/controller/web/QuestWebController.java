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
        String name = getLoggedUser().getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser());

        handleWebQuest(accessLevel, name, sideMenu);
    }

    private void handleWebQuest(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals("student")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    prepareStudentOptions("quests"),
                    sqlQuest.getQuestMap(getLoggedUser().getID(), "rmbk"));
            send200(response);

        } else if (accessLevel.equals("mentor")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    prepareMentorOptions("quests"),
                    null);
            send200(response);

        } else if (accessLevel.equals("admin")) {
            send403();
        }
    }

}
