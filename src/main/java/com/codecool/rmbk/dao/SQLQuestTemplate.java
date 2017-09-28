package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLQuestTemplate extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> getAllQuestTemplates() {

        String query = "SELECT * FROM quest_template";
        processQuery(query);

        return getResults();
    }

    public void addQuestTemplate(String name, String desc, Integer value, Boolean isSpecial) {

        String special = isSpecial ? "1" : "0";
        String query = "INSERT INTO quest_template (name, description, value, special) " +
                       "VALUES ('" + name + "', '" + desc + "', " + value + ", " + special + ");";
        processQuery(query);
    }

    public void editQuestTemplate(String[] data) {

        String name = data[0];
        String query = "UPDATE quest_template " +
                       "SET `description` = '" + data[1] + "', " +
                       "`value` = " + data[2] + ", " +
                       "`special` = '" + data[3] + "', " +
                       "`active` = '" + data[4] + "' " +
                       "WHERE `name` = '" + name + "';";
        processQuery(query);
    }

    public void removeQuestTemplate() {

        ;
    }

}
