package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class UserController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        validateRequest();

        User user = getLoggedUser();
        Map<String, String> sideMenu = sqlMenuDAO.getSideMenu(getLoggedUser());
        response = webDisplay.getSiteContent(user.getFirstName(), sideMenu, user.getFullInfoMap());

        send200(response);
    }
}
