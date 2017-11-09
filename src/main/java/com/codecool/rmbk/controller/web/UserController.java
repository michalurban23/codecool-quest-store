package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class UserController implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String userName = Session.getSessionByCookie(CookieParser.readCookie(httpExchange)).getLoggedUser().getFullName();

        String response = WebDisplay.getSiteContent(userName,
                null, null,"templates/index.twig");

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
