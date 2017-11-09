package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public abstract class CommonHandler implements HttpHandler {

    void send404(HttpExchange httpExchange) throws IOException {
        String response = "404: not found";
        httpExchange.sendResponseHeaders(404, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    void send302(HttpExchange httpExchange, URI location) throws IOException {
        httpExchange.getResponseHeaders().set("Location", location.toString());
        httpExchange.sendResponseHeaders(302, -1);
    }

    void send302(HttpExchange httpExchange, String location) throws IOException {
        httpExchange.getResponseHeaders().set("Location", location);
        httpExchange.sendResponseHeaders(302, -1);
    }

    void send200(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
