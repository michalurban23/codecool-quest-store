package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {

    private HttpServer server;

    public void setup() throws IOException {

        server = HttpServer.create(new InetSocketAddress(8800), 0);

        server.createContext("/logout", new LogoutWebController());
        server.createContext("/login", new LoginWebController());
        server.createContext("/static", new Static());
        server.createContext("/artifacts", new ArtifactWebController());
        server.createContext("/backlog", new BacklogWebController());
        server.createContext("/classes", new ClassWebController());
        server.createContext("/quest", new QuestWebController());
        server.createContext("/teams", new TeamWebController());
        server.createContext("/student", new UserController());
        server.createContext("/mentor", new UserController());
        server.createContext("/admin", new UserController());
        server.createContext("/experience", new ExperienceLevelsController());
        server.createContext("/", new ControllerRouter());
        server.setExecutor(null);
    }

    public void start() {

        server.start();
    }
}
