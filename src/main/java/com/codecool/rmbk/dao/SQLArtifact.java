package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.User;

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

    public Map<String,String> getArtifactMapByUser(User user) {

        Map<String,String> result = new HashMap<>();
        String query = "SELECT `id`, `name` FROM artifacts WHERE `owner` = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {String.valueOf(user.getID())});

        for(ArrayList<String> arr : queryResult.subList(1, queryResult.size())) {
            result.put(arr.get(0), arr.get(1));
        }

        return result;
    }

    public Map<String,String> getArtifactMapByGroup(Group group) {

        Map<String,String> result = new HashMap<>();
        String query = "SELECT `id`, `name` FROM artifacts WHERE `owner` = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {String.valueOf(group.getID())});

        for(ArrayList<String> arr : queryResult.subList(1, queryResult.size())) {
            result.put(arr.get(0), arr.get(1));
        }

        return result;
    }
}
