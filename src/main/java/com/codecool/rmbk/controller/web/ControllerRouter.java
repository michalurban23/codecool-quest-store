package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class ControllerRouter extends CommonHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);
        validateRequest();
        parseURIstring(getRequestURI());

        String controller = user.getClass().getSimpleName().toLowerCase();
        String object = String.valueOf(user.getID());

        if (isObjectInstanceOfController(controller, object)) {
            send302(String.format("/%s/%s", controller, object));
        } else {
            send403();
        }
    }

}
