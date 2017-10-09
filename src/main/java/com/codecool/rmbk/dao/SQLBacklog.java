package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLBacklog extends SqlDAO {

    public ArrayList<ArrayList<String>> getAllBacklogs() {

        String query = "SELECT * FROM backlog";

        // processQuery(query, null);
        return getResults();
    }

    public ArrayList<ArrayList<String>> getBacklog(int id) {

        String query = "SELECT * from backlog WHERE owner = '"+id+"';";//?;";
        String[] data = {""+id};

        // processQuery(query, data);
        processQuery(query);
        return getResults();
    }

    public String getExperience(int id) {

        String query = "SELECT sum(value)" +
                       "FROM backlog" +
                       "WHERE `owner` = ?;";
        String[] data = {""+id};

        processQuery(query, null);
        return getResults().get(1).get(0);
    }
}
