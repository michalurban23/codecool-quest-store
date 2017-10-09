package com.codecool.rmbk.dao;

public class SQLBacklog extends SqlDAO {

    public void getAllBacklogs() {

        String query = "SELECT * FROM backlog";

        processQuery(query, null);
    }

    public void getBackLog(int id) {
        String query = "SELECT * from backlog WHERE owner = ?;";

        processQuery(query, new String[] {"" + id});

    }

    // public void

}
