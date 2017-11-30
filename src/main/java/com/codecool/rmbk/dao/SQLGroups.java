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

    public Group getGroupById(Integer id){

        Group resultGroup = null;
        String query = "SELECT * FROM " + tableName +" WHERE id = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {"" + id});

        if(queryResult.size() > 1) {
            resultGroup = new Team(id, queryResult.get(1).get(1));
        }
        return resultGroup;
    }

    public Group createGroup() {

        String query = "INSERT INTO " + tableName + " (name) VALUES (null);";
        handleQuery(query, null);

        query = "SELECT * FROM " + tableName + " ORDER BY id DESC LIMIT 1;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);

        return getGroupById(Integer.parseInt(queryResult.get(1).get(0)));
    }

    public Boolean removeGroup(Group group) {

        String query = "DELETE FROM " + tableName + " WHERE id = ?;";

        return handleQuery(query, new String[] {"" + group.getID()});
    }

    public Boolean renameGroup(Group group, String newName) {

        String query = "UPDATE " + tableName + " SET name = ? WHERE id = ?;";

        return handleQuery(query, new String[] {newName, "" + group.getID()});
    }

    ArrayList<User> getUsers(Group group, String query) {

        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {"" + group.getID()});

        ArrayList<User> result = new ArrayList<>();
        SQLUsers sqlUsers = new SQLUsers();

        for(ArrayList<String> ar : queryResult.subList(1, queryResult.size())) {
            result.add(sqlUsers.getUserByID(Integer.parseInt(ar.get(0))));
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

    public Map<String,String> getGroupMap(String groupType) {

        Map<String,String> groupsMap = new HashMap<>();

        for(Group group : getAllGroups(groupType)) {
            groupsMap.put(String.format("/%s/%s", groupType, String.valueOf(group.getID())),
                    group.getName());
        }
        return groupsMap;
    }


}
