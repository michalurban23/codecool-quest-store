package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.item.Item;

import java.util.ArrayList;

public class SQLArtifact extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public void getAllArtifacts() {
        String query = "SELECT * FROM artifacts";

        results = processQuery(query);
    }

    public void getArtifact(int id) {
        String query = "SELECT * FROM artifacts WHERE id = '" + id + "';";

        results = processQuery(query);
    }

    public void addArtifact(Item artifact) {

    }

}
