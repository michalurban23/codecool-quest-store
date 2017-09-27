package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLQuest extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getAllQuests() {
        String query = "SELECT * FROM quests";

        handleQuery(query);
        results = getResults();

        return results;
    }

    public ArrayList<ArrayList<String>> getQuestByOwner(int owner) {
        String query = "SELECT * FROM quests WHERE owner = '" + owner + "';";

        handleQuery(query);
        results = getResults();

        return results;
    }
}
