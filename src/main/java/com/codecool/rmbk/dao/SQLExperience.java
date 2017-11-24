package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.StringParser;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SQLExperience extends SqlDAO {

    public Map<String, String> getLevelsMap() {

        Map<String,String> result = new LinkedHashMap<>();

        String query = "SELECT level, value " +
                "FROM experience " +
                "ORDER BY value ASC";

        processQuery(query, null);

        for (ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/experience/" + StringParser.removeWhitespaces(outcome.get(0));
            String name = outcome.get(0);
            result.put(href, name);
        }
        return result;
    }

    public Map<String, String> getLevelInfo(String levelName) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT * " +
                "FROM experience " +
                "WHERE level = ?;";
        String[] data = {StringParser.addWhitespaces(levelName)};

        processQuery(query, data);

        for(int i=0; i<getResults().get(0).size(); i++) {
            String key = getResults().get(0).get(i);
            String value = getResults().get(1).get(i);
            result.put(key, value);
        }
        return result;
    }

    public void addNewLevel(List<String> data) {

        String name = StringUtils.capitalize(StringParser.addWhitespaces(data.get(0)));
        String query = "INSERT INTO experience(level, value) " +
                "VALUES ('" + name + "', ?)";

        processQuery(query, data.subList(1, data.size()).toArray(new String[0]));
    }

    public List<String> getBoundaries(String levelName) {

        String name = StringParser.addWhitespaces(levelName);
        String query = "SELECT value FROM experience " +
                "WHERE value <= (SELECT value FROM experience " +
                "WHERE value > (SELECT value FROM experience " +
                "WHERE level = ?) ORDER BY value ASC LIMIT 1) " +
                "ORDER BY value DESC " +
                "LIMIT 3;";
        String[] data = {name};

        processQuery(query, data);

        List<String> thresholds = new ArrayList<>();

        for (ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            thresholds.add(outcome.get(0));
        }
        thresholds.add(levelName);

        return thresholds;
    }

    public void removeLevel(String name) {

        name = StringParser.addWhitespaces(name);
        String query = "DELETE FROM experience WHERE `level` = ?;";

        processQuery(query, new String[] {name});
    }

    public void editLevels(String object, ArrayList<String> levelData) {

        String name = StringParser.addWhitespaces(levelData.get(0));
        String newValue = levelData.get(1);
        object = StringParser.addWhitespaces(object);

        String query = "UPDATE experience " +
                "SET `value` = ?, `level` = ? " +
                "WHERE `level` = '" + object + "';";
        String[] data = {newValue, name};

        processQuery(query, data);
    }

    public String getExperienceInfo(String totalCoinsEver) {

        String query = "SELECT level FROM experience " +
                "WHERE value <= ? " +
                "ORDER BY value DESC " +
                "LIMIT 1";
        String[] data = {totalCoinsEver};

        processQuery(query, data);

        return getResults().get(1).get(0);
    }

    public String getMissingExp(String totalCoinsEver) {

        String query = "SELECT (value - ?) FROM experience " +
                "WHERE value > ? " +
                "LIMIT 1";
        String[] data = {totalCoinsEver, totalCoinsEver};

        processQuery(query, data);

        return getResults().get(1).get(0);
    }

}
