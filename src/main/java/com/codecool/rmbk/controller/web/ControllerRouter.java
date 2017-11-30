package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class ControllerRouter extends CommonHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);
        validateRequest();
        parseURIstring(getRequestURI());

        String controller = loggedUser.getClass().getSimpleName().toLowerCase();
        String object = String.valueOf(loggedUser.getID());

        send302(String.format("/%s/%s", controller, object));
    }
}
