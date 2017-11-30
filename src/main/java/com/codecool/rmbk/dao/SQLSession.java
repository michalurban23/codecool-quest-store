package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.Session;
import com.codecool.rmbk.model.usr.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SQLSession extends SqlDAO {

    public void addSession(Session session, String id) {

        String query = "INSERT INTO `session` " +
                "VALUES(?, ?, ?, ?, ?);";
        String[] data = {session.getSessionId(),
                id,
                session.getCreateTime().toString(),
                session.getLastAccessTime().toString(),
                session.getExpireTime().toString()};

        handleQuery(query, data);
    }

    public void updateSession(Session session) {

        String query = "UPDATE session " +
                "SET lastActiveTime = ?, expireTime = ? " +
                "WHERE sessionID = ?;";
        String[] data = {session.getLastAccessTime().toString(),
                session.getExpireTime().toString(),
                session.getSessionId()};

        handleQuery(query, data);
    }

    public void removeSession(Session session) {

        String query = "DELETE FROM `session` " +
                "WHERE `sessionID` = ?;";
        String[] data = {session.getSessionId()};

        handleQuery(query, data);
    }

    public void runDatabaseMaintenance() {

        clearExpiredSessions();
        loadActiveSessions();
    }

    private void clearExpiredSessions() {

        String now = LocalDateTime.now().toString();
        String query = "DELETE FROM `session` " +
                "WHERE `expireTime` < ?;";
        String[] data = {now};

        handleQuery(query, data);
    }

    private void loadActiveSessions() {

        SQLUsers userDao = new SQLUsers();
        String query = "SELECT sessionID, userID, creationTime FROM `session`;";

        handleQuery(query, null);

        if (getResults().size() > 1) {
            for (ArrayList<String> session : getResults().subList(1, getResults().size())) {

                String sessionID = session.get(0);
                String userID = session.get(1);
                LocalDateTime createTime = LocalDateTime.parse(session.get(2));
                User user = userDao.getUserByID(Integer.parseInt(userID));

                Session.addSession(sessionID, user, createTime);
            }
        }
    }

}