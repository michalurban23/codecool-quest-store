package com.codecool.rmbk;

import com.codecool.rmbk.controller.LoginController;
import com.codecool.rmbk.controller.web.WebServer;

import java.io.IOException;

public class App {

    public static void main(String[] args) {

        WebServer webServer = new WebServer();
        try {
            webServer.setup();
        } catch (IOException e) {
            System.out.println("q123");
        }

        webServer.start();
    }
}
