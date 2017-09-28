package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLArtifactTemplate extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getAllArtifactTemplates() {

        String query = "SELECT * FROM artifact_template";
        processQuery(query);

        return getResults();
    }

    public void getArtifactTemplate(String name) {
        String query = "SELECT * FROM artifact_template WHERE name = '" + name + "';";

        results = processQuery(query);
    }

    public void addArtifactTemplate(String info) {
        String query = "INSERT INTO artifact_template (name, description, value, special) " + info;

        results = processQuery(query);
    }

    public void removeArtifactTemplate(String name) {
        String query = "DELETE FROM artifact_template WHERE name = '" + name + "';";

        results = processQuery(query);
    }
}
