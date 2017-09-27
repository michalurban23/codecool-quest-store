package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLArtifactTemplate extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getAllArtifactTemplates() {
        String query = "SELECT * FROM artifact_template";

        handleQuery(query);
        results = getResults();

        return results;
    }

    public ArrayList<ArrayList<String>> getArtifactTemplate(String name) {
        String query = "SELECT * FROM artifact_template WHERE name = '" + name + "';";

        handleQuery(query);
        results = getResults();

        return results;
    }
}
