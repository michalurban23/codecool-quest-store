package com.codecool.rmbk.dao;


import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public abstract class SQLGroups extends SqlDAO implements GroupDAO{

    private ArrayList<ArrayList<String>> results;
    String tableName;

    public void getAllGroups() {

        String query = "SELECT * FROM " + tableName + ";";
        processQuery(query, null);
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
        query = String.format("SELECT * FROM " + tableName + " ORDER BY id DESC LIMIT 1;");
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);
        return getGroupById(Integer.parseInt(queryResult.get(1).get(0)));
    }

    public Boolean removeGroup(Group group) {

        String query = "DELETE FROM " + tableName + " groups WHERE id = ?;";
        return handleQuery(query, new String[] {"" + group.getID()});
    }

    public Boolean renameGroup(Group group, String newName) {

        String query = "UPDATE " + tableName + " SET name = ? WHERE id = ?;";
        return handleQuery(query, new String[] {newName, "" + group.getID()});
    }

    public ArrayList<User> getUsers(Group group, String query) {

        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {"" + group.getID()});

        ArrayList<User> result = new ArrayList<>();
        SQLUsers sqlUsers = new SQLUsers();

        for(ArrayList<String> ar : queryResult.subList(1, queryResult.size())) {
            result.add(sqlUsers.getUserByID(Integer.parseInt(ar.get(0))));
        }
        return result;

    }

    public abstract ArrayList<Group> getGroupList(User user);


}
