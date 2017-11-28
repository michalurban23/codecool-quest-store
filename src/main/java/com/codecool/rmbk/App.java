package com.codecool.rmbk;

import com.codecool.rmbk.controller.web.WebServer;
import com.codecool.rmbk.dao.SQLSession;

import java.io.IOException;

public class App {

    public static void main(String[] args) {

        WebServer webServer = new WebServer();
        SQLSession sessionDao = new SQLSession();

        try {
            webServer.setup();
            sessionDao.runDatabaseMaintenance();
        } catch (IOException e) {
            e.printStackTrace();
        }

        webServer.start();
    }
}
