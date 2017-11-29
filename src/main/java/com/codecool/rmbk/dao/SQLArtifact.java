package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.StringParser;
import com.codecool.rmbk.model.usr.Holder;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDateTime;
import java.util.*;

public class SQLArtifact extends SqlDAO {

    public void getAllArtifacts() {

        String query = "SELECT * FROM artifacts";

        processQuery(query, null);
    }

    public void getArtifact(String name) {

        String query = "SELECT * FROM artifacts WHERE id = ?;";

        processQuery(query, new String[] {name});
    }

    public void addArtifact(String templateName, String owner) {

        String query = "INSERT INTO artifacts (template_name, owner) " +
                       "VALUES (?, ?);";

        processQuery(query, new String[]{templateName, owner});
    }

    public Map<String, String> getArtifactMapBy(Holder holder) {

        Map<String, String> result = new HashMap<>();

        String query = "SELECT * " +
                "FROM artifacts " +
                "WHERE `owner` = ?" +
                "AND `return_date` = ''";
        String[] data = {String.valueOf(holder.getID())};

        processQuery(query, data);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/artifacts/" + StringParser.removeWhitespaces(outcome.get(1));
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

    public void updateReturnDate(String owner, String template_name) {

        String now = LocalDateTime.now().toString();
        String query = "UPDATE artifacts " +
        "SET `return_date` = '" + now + "' " +
        "WHERE `template_name` = ?" +
        "AND `owner` = ?";

        processQuery(query, new String[] {StringParser.addWhitespaces(template_name), owner});
    }
}
