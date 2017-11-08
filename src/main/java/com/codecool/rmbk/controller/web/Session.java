package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.model.usr.User;

import java.util.Map;

public class Session {

    private static Map<String, String> activeSessions;
    private User loggedUser;

    public Session() {;}

    public Session(User loggedUser) {

        this.loggedUser = loggedUser;
    }

    public Boolean isActive() {

        return true;
    }
}
