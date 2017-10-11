package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLArtifactTemplate extends SqlDAO {

    public ArrayList<ArrayList<String>> getAllArtifactTemplates() {

        String query = "SELECT * FROM artifact_template";
        processQuery(query, null);

        return getResults();
    }

    public void getArtifactTemplate(String name) {

        String query = "SELECT * FROM artifact_template WHERE name = ?;";

        processQuery(query, new String[] {name});
    }

    public void addArtifactTemplate(String[] info) {

        String query = "INSERT INTO artifact_template (name, owner, completion) VALUES(?, ?, ?);";

        processQuery(query, info);
    }

    public void removeArtifactTemplate(String name) {

        String query = "DELETE FROM artifact_template WHERE name = ?;";

        processQuery(query, new String[] {name});
    }

}
