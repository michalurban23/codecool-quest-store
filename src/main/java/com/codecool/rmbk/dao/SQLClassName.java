package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLClassName extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getAllClasses() {
        String query = "SELECT * FROM class_name";

        handleQuery(query);
        results = getResults();

        return results;
    }

    public ArrayList<ArrayList<String>> getClassName(int id) {
        String query = "SELECT * FROM class_name WHERE id = '" + id + "';";

        handleQuery(query);
        results = getResults();

        return results;
    }
}
