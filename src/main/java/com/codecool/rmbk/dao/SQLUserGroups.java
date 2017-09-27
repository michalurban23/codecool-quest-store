package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLUserGroups extends SqlDAO {
    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getUserGroups() {
        String query = "SELECT * FROM user_groups";

        handleQuery(query);
        results = getResults();

        return results;
    }

    public ArrayList<ArrayList<String>> getUserGroup(int id) {
        String query = "SELECT * FROM user_groups WHERE group_id = '" + id + "';";

        handleQuery(query);
        results = getResults();

        return results;
    }
}
