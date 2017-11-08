package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class BacklogWebController extends CommonHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String response = WebDisplay.getSiteContent("a", null,
                null, "templates/backlog.twig");

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }}
