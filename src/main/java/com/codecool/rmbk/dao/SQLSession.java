package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.Session;

import java.time.LocalDateTime;

public class SQLSession extends SqlDAO {

    public void addSession(Session session, String login) {

        String query = "INSERT INTO `session` " +
                "VALUES(?, ?, ?, ?, ?);";
        String[] data = {session.getSessionId(),
                login,
                session.getCreateTime().toString(),
                session.getLastAccessTime().toString(),
                session.getExpireTime().toString()};

        handleQuery(query, data);
    }

    public void updateSession(Session session) {

        String query = "UPDATE `session` " +
                "SET `lastActiveTime` = ?, `expireTime` = ? " +
                "WHERE `sessionID` = ?;";
        String[] data = {session.getLastAccessTime().toString(),
                session.getExpireTime().toString(),
                session.getSessionId()};

        handleQuery(query, data);
    }

    public Boolean isSessionActive(String sessionId) {

        String query = "SELECT `expireTime` FROM `session` " +
                "WHERE `sessionID` = ?;";
        String[] data = {sessionId};

        handleQuery(query, data);

        if (getResults().size() > 1) {
            String expireTime = getResults().get(1).get(0);
            return LocalDateTime.parse(expireTime).isAfter(LocalDateTime.now());
        }
        return false;
    }

    public void clearExpiredSessions() {

        String now = LocalDateTime.now().toString();
        String query = "DELETE FROM `session` " +
                "WHERE `expireTime` < ?;";
        String[] data = {now};

        handleQuery(query, data);
    }

}
