package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.item.Item;

import java.util.ArrayList;

public class SQLArtifact extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public void getAllArtifacts() {
        String query = "SELECT * FROM artifacts";

        results = processQuery(query);
    }

    public void getArtifact(String name) {
        String query = "SELECT * FROM artifacts WHERE id = '" + name + "';";

        results = processQuery(query);
    }

    public void addArtifact(String info) {
        String query = "INSERT INTO artifacts (template_name, owner, completion) " + info;

        results = processQuery(query);
    }

}
