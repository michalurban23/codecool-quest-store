package com.codecool.rmbk.dao;


import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public class SQLTeam extends SqlDAO implements TeamDAO{

    private ArrayList<ArrayList<String>> results;

    public void getAllGroups() {

        String query = "SELECT * FROM groups";
        processQuery(query, null);
    }

    public Group getTeamByName(String name){

        Team resultGroup = null;
        String query = "SELECT * FROM groups WHERE name = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {name});
        if(queryResult.size() > 1) {
            resultGroup = new Team(Integer.parseInt(queryResult.get(1).get(0)), name);
        }
        return resultGroup;
    }

    public Group getTeamById(Integer id){

        Team resultGroup = null;
        String query = "SELECT * FROM groups WHERE id = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {"" + id});
        if(queryResult.size() > 1) {
            resultGroup = new Team(id, queryResult.get(1).get(1));
        }
        return resultGroup;
    }

    @Override
    public Group createGroup() {

        String query = "INSERT INTO groups (name) VALUES (null);";
        handleQuery(query, null);
        query = String.format("SELECT * FROM groups ORDER BY id DESC LIMIT 1;");
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);
        return getTeamById(Integer.parseInt(queryResult.get(1).get(0)));
    }

    @Override
    public Boolean removeTeam(Group team) {

        String query = "DELETE FROM groups WHERE id = ?;";
        return handleQuery(query, new String[] {"" + team.getID()});
    }

    @Override
    public Boolean renameGroup(Group team, String newName) {

        String query = "UPDATE groups SET name = ? WHERE id = ?;";
        return handleQuery(query, new String[] {newName, "" + team.getID()});
    }

    @Override
    public Boolean addStudentToGroup(Group group, Student student) {

        String query = "INSERT INTO user_groups VALUES(?, ?);";
        return handleQuery(query, new String[] {"" + student.getID(), "" + group.getID()});
    }

    @Override
    public Boolean removeStudentFromGroup(Group group, Student student) {

        String query = "DELETE FROM user_groups WHERE user_id = ? AND group_id = ?;";
        return handleQuery(query, new String[] {"" + student.getID(), "" + group.getID()});
    }

    @Override
    public ArrayList<Team> getTeamList(User user) {

        String query;
        String[] stringSet = null;
        ArrayList<Team> result = new ArrayList<>();

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

        ArrayList<ArrayList<String>> queryResult = processQuery(query,stringSet);
        for(int i=1; i<queryResult.size(); i++) {
            result.add(new Team(Integer.parseInt(queryResult.get(i).get(0)), queryResult.get(i).get(1)));
        }
        return result;
    }

    @Override
    public ArrayList<Student> getStudentsList(Group group) {

        String query = "SELECT * FROM users " +
                       "INNER JOIN user_groups ON user_groups.user_id = users.id " +
                       "WHERE user_groups.group_id = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {"" + group.getID()});

        ArrayList<Student> result = new ArrayList<>();
        SQLUsers sqlUsers = new SQLUsers();

        for(ArrayList<String> ar : queryResult.subList(1, queryResult.size())) {
            result.add((Student) sqlUsers.getUserByID(Integer.parseInt(ar.get(0))));
        }
        return result;
    }

    public Boolean isInGroup(Student user, Group group) {

        String query = "SELECT * FROM user_groups WHERE user_id = ? and group_id = ?;";
        return processQuery(query, new String[] {"" + user.getID(), "" + group.getID()}).size() > 1;
    }

    @Override
    public void updateMembers(Group team) {

        String query = "SELECT * FROM users " +
                "INNER JOIN user_groups ON user_groups.user_id = users.id " +
                "WHERE user_groups.group_id = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {"" + team.getID()});

        SQLUsers sqlUsers = new SQLUsers();
        team.clearMembersList();

        for(ArrayList<String> ar : queryResult.subList(1, queryResult.size())) {
            team.addMember((Student) sqlUsers.getUserByID(Integer.parseInt(ar.get(0))));
        }
    }

    public ArrayList<ArrayList<String>> getUserGroups(User user) {

        String query = "SELECT DISTINCT name FROM users " +
                       "JOIN groups, user_groups " +
                       "WHERE user_groups.user_id = ? AND user_groups.group_id = groups.id";
        String[] data = {""+user.getID()};

        processQuery(query, data);

        return getResults();
    }
}