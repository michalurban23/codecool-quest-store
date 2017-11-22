package com.codecool.rmbk.model;

import com.codecool.rmbk.dao.SQLSession;
import com.codecool.rmbk.model.usr.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private static List<Session> sessions = new ArrayList<>();

    private String sessionId;
    private User user;
    private LocalDateTime createTime = null;
    private LocalDateTime expireTime;
    private LocalDateTime lastAccessTime;
    private int duration = 2; // minutes

    private Session(String sessionId, User user) {

        this.sessionId = sessionId;
        this.user = user;
        this.createTime = LocalDateTime.now();
        this.lastAccessTime = LocalDateTime.now();
        refreshSession();
    }

    private Session(String sessionId, User user, LocalDateTime createTime) {

        this.sessionId = sessionId;
        this.user = user;
        this.createTime = createTime;
        this.lastAccessTime = LocalDateTime.now();
        refreshSession();
    }

    private void refreshSession() {

        this.lastAccessTime = LocalDateTime.now();
        this.expireTime = this.lastAccessTime.plusMinutes(duration);
        refreshInDatabase();
    }

    private void refreshInDatabase() {

        SQLSession sessionDao = new SQLSession();

        sessionDao.updateSession(this);
    }
    public User getUser() {

        return this.user;
    }

    public String getSessionId() {

        return this.sessionId;
    }

    public LocalDateTime getCreateTime() {

        return createTime;
    }

    public LocalDateTime getLastAccessTime() {

        return lastAccessTime;
    }

    public LocalDateTime getExpireTime() {

        return expireTime;
    }

    public static void addSession(String sessionId, User user, LocalDateTime createTime) {

        sessions.add(new Session(sessionId, user, createTime));
    }

    public static Session addSession(String sessionId, User user) {

        Session session = new Session(sessionId, user);
        sessions.add(session);

        return session;
    }

    public static Session removeSession(String sessionId) {

        for (Session session: sessions) {
            if (session.getSessionId().equals(sessionId)) {
                sessions.remove(session);
                return session;
            }
        }
        return null;
    }

    public static Boolean isActive(String sessionId) {

        for (Session s: sessions) {
            if (s.getSessionId().equals(sessionId)) {
                Boolean isActive = s.expireTime.isAfter(LocalDateTime.now());
                if (isActive) {
                    s.refreshSession();
                }
                return isActive;
            }
        }
        return false;
    }

}