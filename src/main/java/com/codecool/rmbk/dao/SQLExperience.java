package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLExperience extends SqlDAO {

    public ArrayList<ArrayList<String>> getExperienceLevels() {
        String query = "SELECT * FROM experience";

        processQuery(query);

        return getResults();
    }

    public void getExperienceInfo(String level) {
        String query = "SELECT * FROM experience WHERE level = '" + level + "';";

        processQuery(query);
    }
}
