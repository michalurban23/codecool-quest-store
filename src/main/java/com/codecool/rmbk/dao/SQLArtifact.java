package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.StringParser;
import com.codecool.rmbk.model.usr.Holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLArtifact extends SqlDAO {

    public void getAllArtifacts() {

        String query = "SELECT * FROM artifacts";

        processQuery(query, null);
    }

    public void getArtifact(String name) {

        String query = "SELECT * FROM artifacts WHERE id = ?;";

        processQuery(query, new String[] {name});
    }

    public void addArtifact(String templateName) {

        String query = "INSERT INTO artifacts template_name " +
                       "VALUES ?;" + templateName;

        processQuery(query, null);
    }

    public Map<String,String> getArtifactMapBy(Holder holder) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT `id`, `template_name` " +
                       "FROM artifacts " +
                       "WHERE `owner` = ?;";
        String[] data = {String.valueOf(holder.getID())};

        processQuery(query, data);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/artifacts/" + outcome.get(0);
            String name = outcome.get(1);
            result.put(href, name);
        }

        return result;
    }

    public Map<String, String> getArtifactInfo(String artifactName) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT * " +
                "FROM artifacts " +
                "WHERE id = ?;";
        String[] data = {StringParser.addWhitespaces(artifactName)};

        processQuery(query, data);

        for(int i=0; i<getResults().get(0).size(); i++) {
            String key = getResults().get(0).get(i);
            String value = getResults().get(1).get(i);
            result.put(key, value);
        }
        return result;
    }

}
