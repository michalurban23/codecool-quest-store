package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.model.usr.User;

import java.net.HttpCookie;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private static List<Session> activeSessions = new ArrayList<>();

    private User loggedUser;
    private String sessionId;
    private LocalDateTime createDate = null;
    private LocalDateTime expireDate;
    private LocalDateTime lastAccessDate;
    private int sessionDurationMinutes = 1;

    Session(User loggedUser, String sessionId) {

        this.loggedUser = loggedUser;
        this.sessionId = sessionId;
        this.lastAccessDate = LocalDateTime.now();
        adjustDateTimeObjects();

        activeSessions.add(this);
    }

    private void adjustDateTimeObjects() {

        if (createDate == null) {
            createDate = lastAccessDate;
        }
        expireDate = LocalDateTime.now().plusMinutes(sessionDurationMinutes);
    }

    public User getLoggedUser() {

        return this.loggedUser;
    }


    public static Session getSessionByCookie(HttpCookie cookie) {

        if (cookie != null) {

            String sessionId = CookieParser.getSessionID(cookie);

            for (Session session : activeSessions) {

                if (session.getSessionId().equals(sessionId)) {
                    return session;
                }
            }
        }
        return null;
    }

    public Boolean isActive() {

        System.out.println("Session is still not expired = " + this.expireDate.isAfter(LocalDateTime.now()));
        Boolean isActive = this.expireDate.isAfter(LocalDateTime.now());
        refreshSession();
        return isActive;
    }

    private void refreshSession() {

        System.out.println("Previous exp time: " + expireDate);
        this.expireDate = LocalDateTime.now().plusMinutes(sessionDurationMinutes);
        System.out.println("Current exp time: " + expireDate);
    }

    public String getAccessLevel() {

        return this.loggedUser.getClass().getSimpleName().toLowerCase();
    }

    private String getSessionId() {

        return this.sessionId;
    }

}
