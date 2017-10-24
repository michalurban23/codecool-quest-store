package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.quest.Quest;
import java.util.ArrayList;

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

    public void getNewQuest(Quest quest) {

        String name = quest.getTemplateName();
        String owner = quest.getOwnerID().toString();
        String today = quest.getStartTime();
        String value = quest.getValue();

        String query = "INSERT INTO quests(template_name, owner, accept_date) VALUES(?, ?, ?);";

        backlog.saveToBacklog(new String[] {today, name, "used", value, owner});
        processQuery(query, new String[] {name, owner, today});
    }

}
