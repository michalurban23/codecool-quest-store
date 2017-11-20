package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.helper.CookieParser;
import com.codecool.rmbk.helper.MimeTypeResolver;
import com.codecool.rmbk.model.Session;
import com.codecool.rmbk.model.usr.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URL;

public abstract class CommonHandler implements HttpHandler {

    void send404(HttpExchange httpExchange) throws IOException {
        URL file404 = getFileURL("./static/404.html");
        if (file404 == null) {
            sendString(httpExchange, "404: not found", 404);
        } else {
            sendFile(httpExchange, file404, 404);
        }
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
        sendString(httpExchange, response, 200);
    }

    void send200(HttpExchange httpExchange, URL fileURL) throws IOException {
        if (fileURL == null) {
            send401(httpExchange);
        } else {
            sendFile(httpExchange, fileURL, 200);
        }

    }

    void send403(HttpExchange httpExchange) throws IOException {
        URL file403 = getFileURL("./static/403.html");
        if (file403 == null) {
            sendString(httpExchange, "403: access denied", 403);
        } else {
            sendFile(httpExchange, file403, 403);
        }

    }

    private void send401(HttpExchange httpExchange) throws IOException {
        URL file401 = getFileURL("./static/401.html");
        if (file401 == null) {
            sendString(httpExchange, "401: session expired", 401);
        } else {
            sendFile(httpExchange, file401, 401);
        }
    }

    String validateRequest(HttpExchange httpExchange) throws IOException {

        HttpCookie cookie = CookieParser.readCookie(httpExchange);
        Session session = Session.getSessionByCookie(cookie);
        String result = null;

        if (session == null) {
            send302(httpExchange, "/login");
        } else if (session.isActive()) {
            result = session.getAccessLevel();
        } else {
            send401(httpExchange);
        }
        return result;
    }

    void sendFile(HttpExchange httpExchange, URL fileURL, int httpCode) throws IOException {

        File file = new File(fileURL.getFile());

        MimeTypeResolver resolver = new MimeTypeResolver(file);
        String mime = resolver.getMimeType();

        httpExchange.getResponseHeaders().set("Content-Type", mime);
        httpExchange.sendResponseHeaders(httpCode, 0);

        OutputStream os = httpExchange.getResponseBody();

        FileInputStream fs = new FileInputStream(file);
        final byte[] buffer = new byte[0x10000];
        int count;
        while ((count = fs.read(buffer)) >= 0) {
            os.write(buffer,0,count);
        }
        os.close();
    }

    void sendString(HttpExchange httpExchange, String response, int httpCode) throws IOException {
        httpExchange.sendResponseHeaders(httpCode, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    URL getFileURL(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(path);
    }

    User getLoggedUser(HttpExchange httpExchange) {

        return Session.getSessionByCookie(CookieParser.readCookie(httpExchange)).getLoggedUser();

    }
}
