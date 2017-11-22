package com.codecool.rmbk.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> getArtifactTemplatesMap() {

        Map<String, String> result = new HashMap<>();

        String query = "SELECT name " +
                "FROM artifact_template";

        processQuery(query, null);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/artifacts/" + removeWhitespaces(outcome.get(0));
            String name = outcome.get(0);
            result.put(name, href);
        }
        return result;
    }

    public Map<String, String> getArtifactInfo(String artifactName) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT * " +
                "FROM artifact_template " +
                "WHERE name = ?;";
        String[] data = {addWhitespaces(artifactName)};

        processQuery(query, data);

        for(int i=0; i<getResults().get(0).size(); i++) {
            String key = getResults().get(0).get(i);
            String value = getResults().get(1).get(i);
            result.put(key, value);
        }
        return result;
    }

    public Map<String, String> getArtifactLabels() {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT * " +
                "FROM artifact_template ";

        processQuery(query, null);

        for(String label : getResults().get(0)) {
            result.put(label, label);
        }

        return result;
    }

    private String removeWhitespaces(String original) {

        StringBuilder newString = new StringBuilder();

        for (char ch: original.toCharArray()) {
            if (ch == ' ') {
                newString.append("_");
            } else {
                newString.append(ch);
            }
        }
        return newString.toString();
    }

    private String addWhitespaces(String original) {

        StringBuilder newString = new StringBuilder();

        for (char ch: original.toCharArray()) {
            if (ch == '_') {
                newString.append(" ");
            } else {
                newString.append(ch);
            }
        }
        return newString.toString();
    }
}
