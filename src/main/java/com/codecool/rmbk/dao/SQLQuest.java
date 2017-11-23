package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.quest.Quest;
import com.codecool.rmbk.model.usr.Holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLQuest extends SqlDAO {

    private SQLBacklog backlog = new SQLBacklog();

    public ArrayList<ArrayList<String>> getMyQuests(Integer id, String teamName) {

        String query;

        if (teamName.equals("solo")) {
            query = "SELECT * FROM quests WHERE owner = ?;";
            processQuery(query, new String[] {"" + id});
        } else {
            query = "SELECT * FROM quests JOIN groups, user_groups " +
                    "ON quests.owner = user_groups.user_id AND groups.id = user_groups.group_id " +
                    "WHERE groups.name = ?;";
            processQuery(query, new String[] {teamName});
        }

        return getResults();
    }

    public Map<String,String> getQuestMap(Integer id, String teamName) {

        List<ArrayList<String>> questList = getMyQuests(id, teamName).subList(1, getResults().size());
        Map<String,String> result = new HashMap<>();
        for(ArrayList<String> arr : questList) {
            result.put(arr.get(0), arr.get(1));
        }
        return result;
    }

    public void getNewQuest(Quest quest) {

        String name = quest.getTemplateName();
        String owner = quest.getOwnerID().toString();
        String today = quest.getStartTime();
        String value = quest.getValue();

        String query = "INSERT INTO quests(template_name, owner, accept_date) VALUES(?, ?, ?);";

        backlog.saveToBacklog(new String[] {today, name, "used", value, owner});
        processQuery(query, new String[] {name, owner, today});
    }

    public Map<String,String> getQuestMapBy(Holder holder) {

        Map<String,String> result = new HashMap<>();

        String query = "SELECT `id`, `template_name` " +
                "FROM quests " +
                "WHERE `owner` = ?;";
        String[] data = {String.valueOf(holder.getID())};

        processQuery(query, data);

        for(ArrayList<String> outcome : getResults().subList(1, getResults().size())) {
            String href = "/quests/" + outcome.get(0);
            String name = outcome.get(1);
            result.put(href, name);
        }

        return result;
    }
}
