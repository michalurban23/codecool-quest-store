package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.model.usr.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Session {

    private static ArrayList<Session> activeSessions;

    private User loggedUser;
    private String sessionID;
    private LocalDateTime createDate;
    private LocalDateTime expireDate;
    private LocalDateTime lastAccessDate;

    public Session() {;}

    public Session(User loggedUser) {

        this.loggedUser = loggedUser;
    }

    public Session(User loggedUser, String sessionId) {

        this.loggedUser = loggedUser;
        this.sessionID = sessionId;
    }

    public static Map<String, String> getActiveSessions() {

        return activeSessions;
    }

    public User getLoggedUser() {

        return loggedUser;
    }

    public String getSessionID() {

        return sessionID;
    }

    public Boolean isActive() {

        return true;
    }
}
