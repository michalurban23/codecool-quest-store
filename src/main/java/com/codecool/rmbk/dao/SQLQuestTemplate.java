package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.StringParser;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SQLQuestTemplate extends SqlDAO {

    public ArrayList<ArrayList<String>> getAllQuestTemplates() {

        String query = "SELECT * FROM quest_template " +
                "ORDER BY name ASC";
        processQuery(query, null);

        return getResults();
    }

    public List<String> getTemplateLabels() {

        List<String> result = new ArrayList<>();

        String query = "SELECT * " +
                "FROM quest_template ";

        processQuery(query, null);

        result.addAll(getResults().get(0));

        return result;
    }

    public Map<String, String> getTemplatesMap() {

        Map<String,String> result = new TreeMap<>();

        String query = "SELECT name " +
                "FROM quest_template " +
                "ORDER BY name ASC";

        processQuery(query, null);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/quests/" + StringParser.removeWhitespaces(outcome.get(0));
            String name = outcome.get(0);
            result.put(href, name);
        }

        return result;
    }

    public Map<String, String> getTemplateInfo(String templateId) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT * " +
                "FROM quest_template " +
                "WHERE name = ?;";
        String[] data = {StringParser.addWhitespaces(templateId)};

        processQuery(query, data);

        for(int i=0; i<getResults().get(0).size(); i++) {
            String key = getResults().get(0).get(i);
            String value = getResults().get(1).get(i);
            result.put(key, value);
        }
        return result;
    }

    public void addQuestTemplate(String name, String desc, Integer value, Boolean isSpecial) {

        String special = isSpecial ? "1" : "0";
        name = StringUtils.capitalize(StringParser.addWhitespaces(name));
        String query = "INSERT INTO quest_template (name, description, value, special) " +
                "VALUES (?, ?, ?, ?)";

        processQuery(query, new String[] {name, desc, "" + value, special});
    }

    public void addQuestTemplate(List<String> data) {

        String name = StringUtils.capitalize(StringParser.addWhitespaces(data.get(0)));
        String query = "INSERT INTO quest_template (name, description, value, special, active) " +
                "VALUES ('" + name + "', ?, ?, ?, ?)";

        processQuery(query, data.subList(1, data.size()).toArray(new String[0]));
    }

    public void editQuestTemplate(String originalName, List<String> data) {

        originalName = StringParser.addWhitespaces(originalName);
        String query = "UPDATE quest_template " +
                       "SET `name` = ?, `description` = ?, `value` = ?, `special` = ?, `active` = ? " +
                       "WHERE `name` = '" + originalName + "';";
        processQuery(query, data.toArray(new String[0]));
    }

    public void removeQuestTemplate(String name) {

        name = StringParser.addWhitespaces(name);
        String query = "DELETE FROM quest_template WHERE `name` = ?;";

        processQuery(query, new String[] {name});
    }

}
