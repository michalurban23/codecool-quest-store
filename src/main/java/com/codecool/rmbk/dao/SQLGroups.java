package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class SQLGroups extends SqlDAO implements GroupDAO {

    String tableName;

    public ArrayList<Group> getAllGroups(String groupType) {

        ArrayList<Group> result = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + ";";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);

        for (ArrayList<String> record : queryResult.subList(1, queryResult.size())) {
            if (groupType.toLowerCase().equals("class")) {
                result.add(new Klass(Integer.parseInt(record.get(0)), record.get(1)));
            } else if (groupType.toLowerCase().equals("team")){
                result.add(new Team(Integer.parseInt(record.get(0)), record.get(1)));
            }
        }
        return result;
    }





    void updateUsers(Group group, String query) {

        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {"" + group.getID()});

        SQLUsers sqlUsers = new SQLUsers();
        group.clearMembersList();

        for(ArrayList<String> ar : queryResult.subList(1, queryResult.size())) {
            group.addMember((Student) sqlUsers.getUserByID(Integer.parseInt(ar.get(0))));
        }

    }




}
