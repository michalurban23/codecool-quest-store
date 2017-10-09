package com.codecool.rmbk.dao;


import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public class SQLTeam extends SqlDAO implements TeamDAO{

    private ArrayList<ArrayList<String>> results;

    public void getAllGroups() {

        String query = "SELECT * FROM groups";
        processQuery(query, null);
    }

    public void getGroupByName(String name) {

        String query = "SELECT * FROM groups WHERE name = ?;";
        processQuery(query, new String[] {name});
    }

    public Team getTeamById(Integer id){

        Team resultGroup = null;
        String query = "SELECT * FROM groups WHERE id = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {"" + id});
        if(queryResult.size() > 1) {
            resultGroup = new Team(id, queryResult.get(1).get(1));
        }
        return resultGroup;
    }

    @Override
    public Team createGroup() {

        String query = "INSERT INTO groups";
        handleQuery(query, null);
        query = String.format("SELECT * FROM groups ORDER BY id DESC LIMIT 1;");
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);
        return getTeamById(Integer.parseInt(queryResult.get(1).get(0)));
    }

    @Override
    public Boolean removeTeam(Team team) {

        String query = "DELETE FROM groups WHERE id = ?;";
        return handleQuery(query, new String[] {"" + team.getID()});
    }

    @Override
    public Boolean renameGroup(Team team, String newName) {

        String query = "UPDATE groups SET name = ? WHERE id = ?;";
        return handleQuery(query, new String[] {newName, "" + team.getID()});
    }

    @Override
    public Boolean addStudentToGroup(Team group, User student) {
        return null;
    }

    @Override
    public Boolean removeStudentFromGroup(Team group, User student) {
        return null;
    }

    public ArrayList<ArrayList<String>> getTeamList(User user) {

        String query;
        String[] stringSet = null;

        if(user.getClass().getSimpleName().equals("Mentor")) {
            query = "SELECT * FROM groups;";

        } else {
            query = "SELECT id_group, group_name FROM users " +
                    "LEFT JOIN (SELECT name AS group_name, user_id, id AS id_group FROM groups " +
                    "LEFT JOIN user_groups " +
                    "ON group_id = id) " +
                    "ON user_id = id " +
                    "WHERE status = 'Student' AND id = ?;";
            stringSet = new String[] {"" + user.getID()};
        }
        return processQuery(query, stringSet);
    }
}