package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.StringParser;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        name = StringParser.addWhitespaces(name);

        String query = "DELETE FROM artifact_template WHERE name = ?;";

        processQuery(query, new String[] {name});
    }

    public Map<String, String> getArtifactTemplatesMap() {

        Map<String, String> result = new HashMap<>();

        String query = "SELECT name " +
                "FROM artifact_template";

        processQuery(query, null);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/artifacts/" + StringParser.removeWhitespaces(outcome.get(0));
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
        String[] data = {StringParser.addWhitespaces(artifactName)};

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

    public Map<String, String> getArtifactTemplatesWithDescription() {

        Map<String, String> result = new HashMap<>();

        String query = "SELECT `name`, `value` " +
                "FROM artifact_template ";

        processQuery(query, null);

        for (ArrayList<String> template : getResults().subList(1, getResults().size())) {
            result.put(template.get(0), template.get(1));
        }
        return result;
    }

    public void addArtifactTemplate(List<String> data) {

        String name = StringUtils.capitalize(StringParser.addWhitespaces(data.get(0)));
        String query = "INSERT INTO artifact_template (name, description, value, special, active) " +
                "VALUES ('" + name + "', ?, ?, ?, ?)";

        processQuery(query, data.subList(1, data.size()-1).toArray(new String[0]));
    }

    public void editArtifactTemplate(String originalName, List<String> data) {

        originalName = StringParser.addWhitespaces(originalName);
        String query = "UPDATE quest_template " +
                "SET `name` = ?, `description` = ?, `value` = ?, `special` = ?, `active` = ? " +
                "WHERE `name` = '" + originalName + "';";
        processQuery(query, data.toArray(new String[0]));
    }

    public Integer getTemplateValue(String name) {

        String query = "SELECT * FROM artifact_template WHERE name = '" + name + "';";
        processQuery(query, null);
        System.out.println(getResults());
        Integer value = Integer.parseInt(getResults().get(1).get(0));
        return value;
    }
}
