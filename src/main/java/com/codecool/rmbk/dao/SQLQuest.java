package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLQuest extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public void getAllQuests() {
        String query = "SELECT * FROM quests";

        processQuery(query);
    }

    public ArrayList<ArrayList<String>> getQuestByOwner(int owner) {
        String query = "SELECT * FROM quests WHERE owner = '" + owner + "';";

        processQuery(query);
    }
}
