package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.StringParser;
import com.codecool.rmbk.model.item.Item;
import com.codecool.rmbk.model.usr.Holder;
import com.codecool.rmbk.model.usr.User;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDateTime;
import java.util.*;

public class SQLArtifact extends SqlDAO {

    private SQLBacklog backlog = new SQLBacklog();

    public Map<String, String> getArtifactMapBy(Holder holder) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT `id`, `template_name` " +
                "FROM artifacts " +
                "WHERE `owner` = ? AND return_date IS NULL;";
        String[] data = {String.valueOf(holder.getID())};

        processQuery(query, data);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/artifacts/" + outcome.get(0);
            String name = outcome.get(1);
            result.put(href, name);
        }
        return result;
    }

    public Map<String, String> getArtifactInfo(String artifactName) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT * " +
                "FROM artifacts " +
                "WHERE id = ?;";
        String[] data = {StringParser.addWhitespaces(artifactName)};

        processQuery(query, data);

        for(int i=0; i<getResults().get(0).size(); i++) {
            String key = getResults().get(0).get(i);
            String value = getResults().get(1).get(i);
            result.put(key, value);
        }
        return result;
    }

    public void buyArtifact(Item artifact) {

        String name = artifact.getTemplateName();
        String owner = artifact.getOwner().toString();
        String today = artifact.getBuyTime();
        String value = artifact.getOriginalValue();

        String query = "UPDATE `artifacts` " +
                "SET return_date = ? " +
                "WHERE owner = ? AND template_name = ?;";

        System.out.println(today + ".." + name + ".." + value + ".." + owner);
        backlog.saveToBacklog(new String[] {today, name, "requested", value, owner});
        processQuery(query, new String[] {today, owner, name});
    }

    public void getNewArtifact(Item artifact) {

        String name = artifact.getTemplateName();
        String owner = artifact.getOwner().toString();
        String today = artifact.getBuyTime();
        String value = artifact.getOriginalValue();

        String query = "INSERT INTO artifacts(template_name, owner) VALUES(?, ?);";

        backlog.saveToBacklog(new String[] {today, name, "bought", value, owner});
        processQuery(query, new String[] {name, owner});
    }

    public void acceptArtifact(Item artifact) {

        String name = artifact.getTemplateName();
        String owner = artifact.getOwner().toString();
        String today = artifact.getBuyTime();
        String value = artifact.getOriginalValue();

        String query = "UPDATE `artifacts` " +
                "SET accept_date = ? " +
                "WHERE owner = ? AND template_name = ?;";

        backlog.saveToBacklog(new String[] {today, name, "used", value, owner});
        processQuery(query, new String[] {today, owner, name});
    }

    public Map<String,String> getBoughtArtifactsMapBy(Holder holder) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT `id`, `template_name` " +
                "FROM artifacts " +
                "WHERE `owner` = ? AND accept_date IS NULL AND return_date IS NOT NULL;";
        String[] data = {String.valueOf(holder.getID())};

        processQuery(query, data);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/artifacts/" + outcome.get(0);
            String name = outcome.get(1);
            result.put(href, name);
        }

        return result;
    }

    public Map<String,String> getAllBoughtArtifactsMap() {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT `id`, `template_name` " +
                "FROM artifacts " +
                "WHERE accept_date IS NULL AND return_date IS NOT NULL;";

        processQuery(query, null);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/artifacts/" + outcome.get(0);
            String name = outcome.get(1);
            result.put(href, name);
        }

        return result;
    }

}
