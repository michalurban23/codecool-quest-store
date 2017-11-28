package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public class TeamWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {
      
        setConnectionData(httpExchange);

        String accessLevel = validateRequest();
        String name = user.getFirstName();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);

        handleWebTeam(accessLevel, name, sideMenu);
    }

    private void handleWebTeam(String accessLevel, String name, Map<String, String> sideMenu) throws IOException {

        if (accessLevel.equals("Student")) {
            response = webDisplay.getSiteContent(name, sideMenu,
                    null,
                    null, "templates/list_content.twig");
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
