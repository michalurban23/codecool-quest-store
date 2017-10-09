package com.codecool.rmbk.dao;


import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public class SQLTeam extends SqlDAO implements TeamDAO{

    private ArrayList<ArrayList<String>> results;

    public void getAllGroups() {

        String query = "SELECT * FROM groups";
        processQuery(query);
    }

    public void getGroupByName(String name) {

        String query = "SELECT * FROM groups WHERE name = '" + name + "';";
        processQuery(query);
    }

    public Team getTeamById(Integer id){

        Team resultGroup = null;
        String query = String.format("SELECT * FROM groups WHERE id = %d;", id);
        ArrayList<ArrayList<String>> queryResult = processQuery(query);
        if(queryResult.size() > 1) {
            resultGroup = new Team(id, queryResult.get(1).get(1));
        }
        return resultGroup;
    }

    @Override
    public Team createGroup() {

        String query = "INSERT INTO groups";
        handleQuery(query);
        query = String.format("SELECT * FROM groups ORDER BY id DESC LIMIT 1;");
        ArrayList<ArrayList<String>> queryResult = processQuery(query);
        return getTeamById(Integer.parseInt(queryResult.get(1).get(0)));
    }

    @Override
    public Boolean removeTeam(Team team) {

        String query = String.format("DELETE FROM groups WHERE id = %d;", team.getId());
        return handleQuery(query);
    }

    @Override
    public Boolean renameGroup(Team team, String newName) {

        String query = String.format("UPDATE groups SET name = '%s' WHERE id = %d;", newName, team.getId());
        return handleQuery(query);
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
        if(user.getClass().getSimpleName().equals("Mentor")) {
            query = "SELECT * FROM groups;";
        } else {
            query = "SELECT id_group, group_name FROM users " +
                    "LEFT JOIN (SELECT name AS group_name, user_id, id AS id_group FROM groups " +
                    "LEFT JOIN user_groups " +
                    "ON group_id = id) " +
                    "ON user_id = id " +
                    "WHERE status = 'Student' AND id = " + user.getID() + ";";
        }
        return processQuery(query);
    }
}
