package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLBacklog extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getBackLog(int id) {
        String query = "SELECT * from backlog WHERE owner = '" + id + "';";

        handleQuery(query);
        results = getResults();
        return results;
    }

}
