package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SQLQuestTemplate extends SqlDAO {

    public ArrayList<ArrayList<String>> getAllQuestTemplates() {

        String query = "SELECT * FROM quest_template";
        processQuery(query, null);

        return getResults();
    }

    public Map<String, String> getTemplatesMap() {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT name " +
                "FROM quest_template ";

        processQuery(query, null);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/quests/" + removeWhitespaces(outcome.get(0));
            String name = outcome.get(0);
            result.put(name, href);
        }

        return result;
    }

    private String removeWhitespaces(String original) {

        StringBuilder newString = new StringBuilder();

        for (char ch: original.toCharArray()) {
            if (ch == ' ') {
                newString.append("_");
            } else {
                newString.append(ch);
            }
        }
        return newString.toString();
    }

    private String addWhitespaces(String original) {

        StringBuilder newString = new StringBuilder();

        for (char ch: original.toCharArray()) {
            if (ch == '_') {
                newString.append(" ");
            } else {
                newString.append(ch);
            }
        }
        return newString.toString();
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
