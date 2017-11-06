package com.codecool.rmbk.controller;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {

    private HttpServer server;

    private void setup() throws IOException {

        server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/admin", new AdminWebController());
        server.createContext("/artifacts", new ArtifactWebController());
        server.createContext("/backlog", new BacklogWebController());
        server.createContext("/class", new ClassWebController());
        server.createContext("/group", new GroupWebController());
        server.createContext("/login", new LoginWebController());
        server.createContext("/mentor", new MentorWebController());
        server.createContext("/quest", new QuestWebController());
        server.createContext("/shopping", new ShoppingWebController());
        server.createContext("/student", new StudentWebController());
        server.createContext("/team", new TeamWebController());
        server.createContext("/user", new UserWebController());
        server.setExecutor(null);
    }

    private void start() {

        server.start();
    }
}
