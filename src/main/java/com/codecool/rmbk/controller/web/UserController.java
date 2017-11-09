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

        Session session = Session.getSessionByCookie(CookieParser.readCookie(httpExchange));
        System.out.println(validateRequest(httpExchange));

        if (validateRequest(httpExchange) != null) {
            String userName = session.getLoggedUser().getFullName();
            String response = WebDisplay.getSiteContent(userName, sqlMenuDAO.getSideMenu(getLoggedUser(httpExchange)),
                    null,"templates/index.twig");
            send200(httpExchange, response);
        }
    }
}
