package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class UserWebController extends CommonHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String response = WebDisplay.getSiteContent("a", null,
                null, "templates/mentor_artifacts.twig");

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
