package com.codecool.rmbk.model;

import java.time.LocalDateTime;

public class Session {

    private String sessionId;
    private LocalDateTime createTime = null;
    private LocalDateTime expireTime;
    private LocalDateTime lastAccessTime;
    private int sessionDurationMinutes = 2;

    public Session(String sessionId) {

        this.sessionId = sessionId;
        this.lastAccessTime = LocalDateTime.now();
        adjustDateTimeObjects();
    }

    private void adjustDateTimeObjects() {

        if (createTime == null) {
            createTime = lastAccessTime;
        }
        expireTime = LocalDateTime.now().plusMinutes(sessionDurationMinutes);
    }

    public Boolean isActive() {

        Boolean isActive = this.expireTime.isAfter(LocalDateTime.now());
        refreshSession();
        return isActive;
    }

    private void refreshSession() {

        this.expireTime = LocalDateTime.now().plusMinutes(sessionDurationMinutes);
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

}