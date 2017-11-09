package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;

public class LogoutWebController extends CommonHandler {

    public void handle(HttpExchange httpExchange) {

        Session.getSessionByCookie(CookieParser.readCookie(httpExchange)).clearCache();
    }
}
