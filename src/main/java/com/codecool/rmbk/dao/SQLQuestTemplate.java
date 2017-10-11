package com.codecool.rmbk.dao;

import java.util.ArrayList;
import java.util.Arrays;

public class SQLQuestTemplate extends SqlDAO {

    public ArrayList<ArrayList<String>> getAllQuestTemplates() {

        String query = "SELECT * FROM quest_template";
        processQuery(query, null);

        return getResults();
    }

    public void addQuestTemplate(String name, String desc, Integer value, Boolean isSpecial) {

        String special = isSpecial ? "1" : "0";
        String query = "INSERT INTO quest_template (name, description, value, special) VALUES (?, ?, ?, ?)";
        processQuery(query, new String[] {name, desc, "" + value, special});
    }

    public void editQuestTemplate(String[] data) {

        String name = data[0];
        String query = "UPDATE quest_template " +
                       "SET `description` = ?, `value` = ?, `special` = ?, `active` = ? " +
                       "WHERE `name` = '" + name + "';";
        processQuery(query, Arrays.copyOfRange(data, 1, 4));
    }

    public void removeQuestTemplate(String name) {

        String query = "DELETE FROM quest_template WHERE `name` = ?;";
        processQuery(query, new String[] {name});
    }

}
