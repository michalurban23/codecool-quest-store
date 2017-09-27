package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLQuestTemplate extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getQuestTemplates() {
        String query = "SELECT * FROM quest_template";

        handleQuery(query);
        results = getResults();

        return results;
    }

    public ArrayList<ArrayList<String>> getQuestTemplate(String name) {
        String query = "SELECT * FROM quest_template WHERE name = '" + name + "';";

        handleQuery(query);
        results = getResults();

        return results;
    }

}
