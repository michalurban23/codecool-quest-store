package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {

    private HttpServer server;

    public void setup() throws IOException {

        server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/index", new UserController());
        server.createContext("/login", new LoginWebController());
        server.createContext("/static", new Static());
        server.createContext("/artifacts", new ArtifactWebController());
        server.createContext("/backlog", new BacklogWebController());
        server.createContext("/class", new ClassWebController());
        server.createContext("/group", new GroupWebController());
        server.createContext("/quest", new QuestWebController());
        server.createContext("/shopping", new ShoppingWebController());
        server.createContext("/team", new TeamWebController());
        server.createContext("/user", new UserWebController());
        server.setExecutor(null);
    }

    public void start() {

        server.start();
    }
}
