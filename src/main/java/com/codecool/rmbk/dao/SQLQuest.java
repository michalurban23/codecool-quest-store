package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.quest.Quest;

import java.util.ArrayList;

public class SQLQuest extends SqlDAO {

    public ArrayList<ArrayList<String>> getMyQuests(Integer id) {

        String query = "SELECT * FROM quests WHERE owner = ?;";
        processQuery(query, new String[] {"" + id});
        return getResults();
    }

    public void getNewQuest(Quest quest) {

        String query = "INSERT INTO quests(template_name, owner, accept_date) VALUES(?, ?, ?);";
        processQuery(query, new String[] {quest.getTemplateName(), "" + quest.getOwnerID(), quest.getStartTime()});
    }

}
