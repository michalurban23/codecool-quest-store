package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLExperience extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getExperienceLevels() {
        String query = "SELECT * FROM experience";

        handleQuery(query);
        results = getResults();

        return results;
    }

    public ArrayList<ArrayList<String>> getExperienceInfo(String level) {
        String query = "SELECT * FROM experience WHERE level = '" + level + "';";

        handleQuery(query);
        results = getResults();

        return results;
    }
}
