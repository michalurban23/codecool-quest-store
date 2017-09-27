package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLExperience extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public void getExperienceLevels() {
        String query = "SELECT * FROM experience";

        processQuery(query);
    }

    public void getExperienceInfo(String level) {
        String query = "SELECT * FROM experience WHERE level = '" + level + "';";

        processQuery(query);
    }
}
