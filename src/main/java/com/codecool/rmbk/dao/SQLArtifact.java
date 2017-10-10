package com.codecool.rmbk.dao;

public class SQLArtifact extends SqlDAO {

    private SQLBacklog backlog = new SQLBacklog();

    public void getAllArtifacts() {

        String query = "SELECT * FROM artifacts";

        processQuery(query, null);
    }

    public void getArtifact(String name) {

        String query = "SELECT * FROM artifacts WHERE id = ?;";

        processQuery(query, new String[] {name});
    }

    public void addArtifact(String[] info) {

        String query = "INSERT INTO artifacts (template_name, owner, completion) " +
                       "VALUES (?, ?, ?);" + info;

        backlog.saveToBacklog(new String[] {null, info[0], "bought", info[2], info[1]}); // --- change null to date ---
        processQuery(query, info);
    }

}
