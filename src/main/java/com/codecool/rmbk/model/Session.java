package com.codecool.rmbk.model;

import com.codecool.rmbk.helper.CookieParser;
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
    private int sessionDurationMinutes = 10;

    public Session(User loggedUser, String sessionId) {

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

        Boolean isActive = this.expireDate.isAfter(LocalDateTime.now());
        refreshSession();
        return isActive;
    }

    private void refreshSession() {

        this.expireDate = LocalDateTime.now().plusMinutes(sessionDurationMinutes);
    }

    public String getAccessLevel() {

        return this.loggedUser.getClass().getSimpleName().toLowerCase();
    }

    public String getSessionId() {

        return this.sessionId;
    }

    public static void clearCache() {

        activeSessions.clear();
    }

}
