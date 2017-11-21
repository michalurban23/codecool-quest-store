package com.codecool.rmbk.dao;

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

    public void addArtifact(String[] info) {

        String query = "INSERT INTO artifacts (template_name, owner, completion) " +
                       "VALUES (?, ?, ?);" + info;

        processQuery(query, info);
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
            result.put(name, href);
        }

        return result;
    }
}
