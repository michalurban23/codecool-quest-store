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
        System.out.println(activeSessions.size());
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

                    session.refreshSession();
                    return session;
                }
            }
        }
        return null;
    }

    public Boolean isActive() {

        return this.expireDate.isAfter(LocalDateTime.now());
    }

    private void refreshSession() {

        this.expireDate = LocalDateTime.now().plusMinutes(sessionDurationMinutes);
    }

    public String getAccessLevel() {

        return this.loggedUser.getClass().getSimpleName().toLowerCase();
    }

    private String getSessionId() {

        return this.sessionId;
    }

}
