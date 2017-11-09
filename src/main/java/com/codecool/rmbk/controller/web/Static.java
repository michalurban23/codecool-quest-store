package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.helper.MimeTypeResolver;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

public class Static extends CommonHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        URI uri = httpExchange.getRequestURI();
        String path = "." + uri.getPath();
        send200(httpExchange, getFileURL(path));
    }
}