package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.item.ItemTemplate;

import java.util.ArrayList;

public class SQLArtifactTemplate extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getAllArtifactTemplates() {

        String query = "SELECT * FROM artifact_template";
        processQuery(query);

        return getResults();
    }

    public void getArtifactTemplate(String name) {
        String query = "SELECT * FROM artifact_template WHERE name = '" + name + "';";

        processQuery(query);
    }

    public void addArtifactTemplate(ItemTemplate template) {

    }

    public void editArtifactTemplate(ItemTemplate template) {

    }
}
