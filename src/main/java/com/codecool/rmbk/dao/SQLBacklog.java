package com.codecool.rmbk.dao;

public class SQLBacklog extends SqlDAO {

    public void getAllBacklogs() {

        String query = "SELECT * FROM backlog";

        processQuery(query, null);
    }

    public void getBacklog(int id) {

        String query = "SELECT * from backlog WHERE owner = ?;";
        String[] data = {id.toString()};

        processQuery(query, data);
    }

    // public void

}
