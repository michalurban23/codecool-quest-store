package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLUserGroups extends SqlDAO {
    private ArrayList<ArrayList<String>> results;

    public void getUserGroups() {
        String query = "SELECT * FROM user_groups";

        processQuery(query);
    }

    public void getUserGroup(int id) {
        String query = "SELECT * FROM user_groups WHERE group_id = '" + id + "';";

        processQuery(query);
    }
}
