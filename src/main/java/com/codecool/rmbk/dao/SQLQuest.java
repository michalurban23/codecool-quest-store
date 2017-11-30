package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.StringParser;
import com.codecool.rmbk.model.quest.Quest;
import com.codecool.rmbk.model.usr.Holder;

import java.util.*;

public class SQLQuest extends SqlDAO {

    private SQLBacklog backlog = new SQLBacklog();

    public void getNewQuest(Quest quest) {

        String name = quest.getTemplateName();
        String owner = quest.getOwnerID().toString();
        String today = quest.getStartTime();
        String value = quest.getValue();

        String query = "INSERT INTO quests(template_name, owner) VALUES(?, ?);";

        backlog.saveToBacklog(new String[] {today, name, "acquired", value, owner});
        processQuery(query, new String[] {name, owner});
    }

    public void submitQuest(Quest quest) {

        String name = quest.getTemplateName();
        String owner = quest.getOwnerID().toString();
        String today = quest.getStartTime();
        String value = quest.getValue();

        String query = "UPDATE `quests` " +
                "SET return_date = ? " +
                "WHERE owner = ? AND template_name = ?;";

        backlog.saveToBacklog(new String[] {today, name, "submitted", value, owner});
        processQuery(query, new String[] {today, owner, name});
    }

    public void acceptQuest(Quest quest) {

        String name = quest.getTemplateName();
        String owner = quest.getOwnerID().toString();
        String today = quest.getStartTime();
        String value = quest.getValue();

        String query = "UPDATE `quests` " +
                "SET accept_date = ? " +
                "WHERE owner = ? AND template_name = ?;";

        backlog.saveToBacklog(new String[] {today, name, "accepted", value, owner});
        processQuery(query, new String[] {today, owner, name});
    }

    public Map<String,String> getQuestMapBy(Holder holder) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT `id`, `template_name` " +
                "FROM quests " +
                "WHERE `owner` = ? AND return_date IS NULL;";
        String[] data = {String.valueOf(holder.getID())};

        processQuery(query, data);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/quests/" + outcome.get(0);
            String name = outcome.get(1);
            result.put(href, name);
        }

        return result;
    }

    public Map<String,String> getSubmittedQuestMapBy(Holder holder) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT `id`, `template_name` " +
                "FROM quests " +
                "WHERE `owner` = ? AND accept_date IS NULL AND return_date IS NOT NULL;";
        String[] data = {String.valueOf(holder.getID())};

        processQuery(query, data);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/quests/" + outcome.get(0);
            String name = outcome.get(1);
            result.put(href, name);
        }

        return result;
    }

    public Map<String,String> getAllSubmittedQuestsMap() {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT `id`, `template_name` " +
                "FROM quests " +
                "WHERE accept_date IS NULL AND return_date IS NOT NULL;";

        processQuery(query, null);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/quests/" + outcome.get(0);
            String name = outcome.get(1);
            result.put(href, name);
        }

        return result;
    }

    public Map<String, String> getQuestInfo(String templateId) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT (first_name || ' ' || last_name) as fullname, " +
                "template_name, accept_date, return_date, quest_template.value, owner " +
                "FROM quests " +
                "JOIN quest_template " +
                "JOIN users " +
                "ON quests.template_name = quest_template.name AND users.id = quests.owner " +
                "WHERE quests.id = ?;";
        String[] data = {StringParser.addWhitespaces(templateId)};

        processQuery(query, data);

        for(int i=0; i<getResults().get(0).size(); i++) {
            String key = getResults().get(0).get(i);
            String value = getResults().get(1).get(i);
            result.put(key, value);
        }
        return result;
    }

    public Map<String, String> getAvailableQuests(Holder holder) {

        Map<String,String> result = new TreeMap<>();

        String query = "SELECT name, value FROM quest_template " +
                "WHERE name NOT IN " +
                "(SELECT template_name FROM quests WHERE owner = ? AND accept_date IS NULL);";
        String[] data = {"" + holder.getID()};

        processQuery(query, data);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String name = outcome.get(0);
            String value = outcome.get(1);
            result.put(name, value);
        }

        return result;
    }

}
