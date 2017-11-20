package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.model.Session;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class LogoutWebController extends CommonHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        Session.clearCache();
        send302(httpExchange, "/login");
    }
}
