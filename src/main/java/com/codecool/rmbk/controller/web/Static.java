package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URI;

public class Static extends CommonHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        URI uri = httpExchange.getRequestURI();
        String path = "." + uri.getPath();
        send200(getFileURL(path));
    }
}