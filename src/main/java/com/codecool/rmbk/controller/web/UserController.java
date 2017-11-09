package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;


public class UserController extends CommonHandler {

    SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String userName = Session.getSessionByCookie(CookieParser.readCookie(httpExchange)).getLoggedUser().getFullName();

        String response = WebDisplay.getSiteContent(userName, sqlMenuDAO.getSideMenu(getLoggedUser(httpExchange)),
                null,"templates/index.twig");

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
