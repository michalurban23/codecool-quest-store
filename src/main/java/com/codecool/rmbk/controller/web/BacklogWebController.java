package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class BacklogWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);

        String response = webDisplay.getSiteContent(user.getFirstName(),
                sqlMenuDAO.getSideMenu(user),
                null, null, "templates/list_content.twig");

        send200(response);
    }

}