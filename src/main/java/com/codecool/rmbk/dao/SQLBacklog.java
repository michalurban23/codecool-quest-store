package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLBacklog extends SqlDAO {

    public ArrayList<ArrayList<String>> getAllBacklogs() {

        String query = "SELECT action_date, description, backlog.status, value," +
                       "       (first_name || ' ' || last_name) as full_name " +
                       "FROM backlog " +
                       "JOIN users " +
                       "ON backlog.owner = users.id " +
                       "ORDER BY `owner` ASC, `action_date`;";

        processQuery(query, null);
        return getResults();
    }

    public ArrayList<ArrayList<String>> getBacklog(int id) {

        String query = "SELECT * from backlog WHERE owner = ?;";

        processQuery(query, new String[] {"" + id});
        return getResults();
    }

    public Integer getCurrentCoins(int id) {

        Integer coins = 0;
        String[] data = {"" + id};

        coins += getEarned(data);
        coins -= getSpent(data);

        return coins;
    }

    private Integer getEarned(String[] data) {

        Integer earned;
        String query = "SELECT SUM(value) AS balance " +
                "FROM backlog WHERE owner = ? AND status = 'used';";
        processQuery(query, data);

        try {
            earned = Integer.parseInt(getResults().get(1).get(0));
        } catch (NumberFormatException e) {
            earned = 0;
        }
        return earned;
    }

    private Integer getSpent(String[] data) {

        Integer spent;
        String query = "SELECT SUM(value) AS balance " +
                "FROM backlog WHERE owner = ? AND status = 'bought';";
        processQuery(query, data);

        try {
            spent = Integer.parseInt(getResults().get(1).get(0));
        } catch (NumberFormatException e) {
            spent = 0;
        }
        return spent;
    }

    public String getExperience(int id) {

        String query = "SELECT sum(value) " +
                       "FROM backlog " +
                       "WHERE `owner` = ? ";
        String[] data = {"" + id};

        processQuery(query, data);
        return getResults().get(1).get(0);
    }

    public void saveToBacklog(String[] data) {

        String query = "INSERT INTO backlog(action_date, description, status, value, owner) " +
                       "VALUES (?, ?, ?, ?, ?);";

        processQuery(query, data);
    }

}
