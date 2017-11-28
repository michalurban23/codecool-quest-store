package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.StringParser;
import com.codecool.rmbk.model.usr.Holder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SQLBacklog extends SqlDAO {

    public Map<String, String> getAllBacklogs() {

        Map<String, String> result = new LinkedHashMap<>();

        String query = "SELECT backlog.id, description, backlog.status, " +
                       "       (first_name || ' ' || last_name) as full_name " +
                       "FROM backlog " +
                       "JOIN users " +
                       "ON backlog.owner = users.id " +
                       "ORDER BY `owner` ASC, `action_date`;";
        processQuery(query, null);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/backlog/" + StringParser.removeWhitespaces(outcome.get(0));
            String name = "<strong>" + outcome.get(3).toUpperCase() + " </strong> " +
                    " <em> " + outcome.get(2) + " </em> &lt&lt" +
                    outcome.get(1) + "&gt&gt";
            result.put(href, name);
        }
        return result;
    }

    public Map<String, String> getBacklogMap(Holder holder) {

        Map<String, String> result = new LinkedHashMap<>();

        String id = String.valueOf(holder.getID());
        String query = "SELECT id, status, description " +
                "FROM backlog " +
                "WHERE owner = ?;";
        String[] data = {id};

        processQuery(query, data);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/backlog/" + StringParser.removeWhitespaces(outcome.get(0));
            String name = "&lt&lt" + outcome.get(1).toUpperCase() + "&gt&gt " + outcome.get(2);
            result.put(href, name);
        }
        return result;
    }

    public Map<String, String> getBacklogDetail(String object) {

        Map<String,String> result = new LinkedHashMap<>();

        String query = "SELECT (first_name || ' ' || last_name) as fullname, " +
                "action_date, backlog.status, description, value " +
                "FROM backlog " +
                "JOIN users ON users.id = backlog.owner " +
                "WHERE backlog.id = ?;";
        String[] data = {StringParser.addWhitespaces(object)};

        processQuery(query, data);

        for(int i=0; i<getResults().get(0).size(); i++) {
            String key = getResults().get(0).get(i);
            String value = getResults().get(1).get(i);
            result.put(key, value);
        }
        return result;
    }

    public ArrayList<ArrayList<String>> getBacklog(int id) {

        String query = "SELECT * from backlog WHERE owner = ?;";

        processQuery(query, new String[] {"" + id});
        return getResults();
    }

    public Integer getCurrentCoins(int id) {

        Integer coins = 0;
        String[] data = {"" + id};

        coins += getEarned(data);
        coins -= getSpent(data);

        return coins;
    }

    private Integer getEarned(String[] data) {

        Integer earned;
        String query = "SELECT SUM(value) AS balance " +
                "FROM backlog WHERE owner = ? AND status = 'accepted';";
        processQuery(query, data);

        try {
            earned = Integer.parseInt(getResults().get(1).get(0));
        } catch (NumberFormatException e) {
            earned = 0;
        }
        return earned;
    }

    private Integer getSpent(String[] data) {

        Integer spent;
        String query = "SELECT SUM(value) AS balance " +
                "FROM backlog WHERE owner = ? AND status = 'bought';";
        processQuery(query, data);

        try {
            spent = Integer.parseInt(getResults().get(1).get(0));
        } catch (NumberFormatException e) {
            spent = 0;
        }
        return spent;
    }

    public String getExperience(int id) {

        String query = "SELECT sum(value) " +
                       "FROM backlog " +
                       "WHERE `owner` = ? AND `status` = 'accepted';";
        String[] data = {"" + id};

        processQuery(query, data);

        String experience = getResults().get(1).get(0);

        return experience != null ? experience : "0";
    }

    public void saveToBacklog(String[] data) {

        String query = "INSERT INTO backlog(action_date, description, status, value, owner) " +
                       "VALUES (?, ?, ?, ?, ?);";

        processQuery(query, data);
    }

}
