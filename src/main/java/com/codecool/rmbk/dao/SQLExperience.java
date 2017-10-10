package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLExperience extends SqlDAO {

    public ArrayList<ArrayList<String>> getExperienceLevels() {

        String query = "SELECT * FROM experience";

        processQuery(query, null);
        return getResults();
    }

    public String getExperienceInfo(String level) {

        String query = "SELECT level FROM experience WHERE value < ? ORDER BY value DESC LIMIT 1;";

        processQuery(query, new String[] {level});

        return getResults().get(1).get(0);
    }
}
