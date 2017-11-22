package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLSession;
import com.codecool.rmbk.model.Session;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class LogoutWebController extends CommonHandler {

    private SQLSession sessionDao = new SQLSession();

    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);

        Session session = Session.removeSession(cookieHandler.getSessionId());
        if (session != null) {
            sessionDao.removeSession(session);
        }

        send302("/login");
    }

}
