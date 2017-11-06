package com.codecool.rmbk.controller;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {

    private HttpServer server;

    private void setup() throws IOException {

        server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/admin", new AdminController());
        server.createContext("/artifacts", new ArtifactController());
        server.createContext("/backlog", new BacklogController());
        server.createContext("/class", new ClassController());
        server.createContext("/group", new GroupController());
        server.createContext("/login", new LoginController());
        server.createContext("/mentor", new MentorController());
        server.createContext("/quest", new QuestController());
        server.createContext("/shopping", new ShoppingController());
        server.createContext("/student", new StudentController());
        server.createContext("/team", new TeamController());
        server.createContext("/user", new UserController());
        server.setExecutor(null);
    }

    private void start() {

        server.start();
    }
}
