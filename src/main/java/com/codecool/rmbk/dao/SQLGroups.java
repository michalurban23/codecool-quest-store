package com.codecool.rmbk.dao;


import java.util.ArrayList;

public class SQLGroups extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public void getAllGroups() {
        String query = "SELECT * FROM groups";

        processQuery(query);
    }

    public void getGroup(String name) {
        String query = "SELECT * FROM groups WHERE name = '" + name + "';";

        processQuery(query);
    }
}
