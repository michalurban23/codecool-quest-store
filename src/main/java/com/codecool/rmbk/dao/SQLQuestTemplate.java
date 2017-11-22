package com.codecool.rmbk.dao;

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

    public Map<String, String> getTemplateLabels() {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT * " +
                "FROM quest_template ";

        processQuery(query, null);

        for(String label : getResults().get(0)) {
            result.put(label, label);
        }

        return result;
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

    public Map<String, String> getTemplateInfo(String templateId) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT * " +
                "FROM quest_template " +
                "WHERE name = ?;";
        String[] data = {addWhitespaces(templateId)};

        processQuery(query, data);

        for(int i=0; i<getResults().get(0).size(); i++) {
            String key = getResults().get(0).get(i);
            String value = getResults().get(1).get(i);
            result.put(key, value);
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

        name = addWhitespaces(name);
        String query = "DELETE FROM quest_template WHERE `name` = ?;";
        processQuery(query, new String[] {name});
    }

}
