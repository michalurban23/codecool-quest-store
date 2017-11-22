package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public class UserController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);
        validateRequest();

        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(user);
        response = webDisplay.getSiteContent(user.getFirstName(), sideMenu, null,
                user.getFullInfoMap(), "templates/main_user.twig");

        send200(response);

    }


}
