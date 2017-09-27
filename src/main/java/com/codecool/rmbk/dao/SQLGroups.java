package com.codecool.rmbk.dao;


import java.util.ArrayList;

public class SQLGroups extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getAllGroups() {
        String query = "SELECT * FROM groups";

        handleQuery(query);
        results = getResults();

        return results;
    }

    public ArrayList<ArrayList<String>> getGroup(String name) {
        String query = "SELECT * FROM groups WHERE name = '" + name + "';";

        handleQuery(query);
        results = getResults();

        return results;
    }
}
