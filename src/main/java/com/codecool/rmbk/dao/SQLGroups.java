package com.codecool.rmbk.dao;

public class SQLGroups extends SqlDAO {

    public void getAllGroups() {

        String query = "SELECT * FROM groups";

        processQuery(query, null);
    }

    public void getGroup(String name) {

        String query = "SELECT * FROM groups WHERE name = ?;";

        processQuery(query, new String[] {name});
    }
}
