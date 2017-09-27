package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLUsers extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getAllUsers() {
        String query = "SELECT * FROM users";

        handleQuery(query);
        results = getResults();

        return results;
    }

    public ArrayList<ArrayList<String>> getUser(int id) {
        String query = "SELECT * from users WHERE id = '" + id + "';";

        handleQuery(query);
        results = getResults();

        return results;
    }

}

