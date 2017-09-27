package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLQuestTemplate extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public void getQuestTemplates() {
        String query = "SELECT * FROM quest_template";

        processQuery(query);
    }

    public void getQuestTemplate(String name) {
        String query = "SELECT * FROM quest_template WHERE name = '" + name + "';";

        processQuery(query);
    }

}
