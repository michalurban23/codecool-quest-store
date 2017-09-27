package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLArtifact extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getAllArtifacts() {
        String query = "SELECT * FROM artifacts";

        handleQuery(query);
        results = getResults();

        return results;
    }

    public ArrayList<ArrayList<String>> getArtifact(int id) {
        String query = "SELECT * FROM artifacts WHERE id = '" + id + "';";

        handleQuery(query);
        results = getResults();

        return results;
    }
}
