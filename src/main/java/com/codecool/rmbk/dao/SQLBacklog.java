package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLBacklog extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public void getAllBacklogs() {
        String query = "SELECT * FROM backlog";

        processQuery(query)
    }

    public void getBackLog(int id) {
        String query = "SELECT * from backlog WHERE owner = '" + id + "';";

        processQuery(query);
    }

}
