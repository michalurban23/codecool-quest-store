package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class LogoutWebController extends CommonHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);

        cookieHandler.setStatusToLoggedOut();
        clearSessionData();
        send302("/login");
    }
}
