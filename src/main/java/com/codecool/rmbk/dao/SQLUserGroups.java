package com.codecool.rmbk.dao;

public class SQLUserGroups extends SqlDAO {

    public void getUserGroups() {

        String query = "SELECT * FROM user_groups";

        processQuery(query, null);
    }

    public void getUserGroup(int id) {

        String query = "SELECT * FROM user_groups WHERE group_id = ?;";

        processQuery(query, new String[] {"" + id});
    }
}
