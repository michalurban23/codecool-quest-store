package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.*;

import java.util.*;

public class SQLTeam extends SqlDAO {

    public ArrayList<Team> getMyTeams(Student user) {

        String query = "SELECT group_id FROM user_groups WHERE user_id = ?;";
        ArrayList<Team> result = new ArrayList<>();

        processQuery(query, new String[] {String.valueOf(user.getID())});

        ArrayList<ArrayList<String>> results = (ArrayList) getResults().clone();
        for (int i = 1 ; i < results.size() ; i++) {
            String id = results.get(i).get(0);
            Team team = getTeamById(Integer.parseInt(id));
            result.add(team);
        }
        return result;
    }

    public ArrayList<Team> getAllTeams() {

        String query = "SELECT id FROM groups;";
        ArrayList<Team> result = new ArrayList<>();

        processQuery(query, null);

        ArrayList<ArrayList<String>> results = (ArrayList) getResults().clone();
        for (int i = 1 ; i < results.size() ; i++) {
            String id = results.get(i).get(0);
            Team team = getTeamById(Integer.parseInt(id));
            result.add(team);
        }
        return result;
    }

    public Team getTeamById(Integer id) {

        Team team = null;
        String query = "SELECT * FROM groups WHERE id = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {"" + id});

        if(queryResult.size() > 1) {
            team = new Team (id, queryResult.get(1).get(1));
            team.setMembers(getStudentsList(team));
        }
        return team;
    }

    public Map<String,String> getTeamURLMap() {

        Map<String,String> groupsMap = new HashMap<>();

        for(Team team : getAllTeams()) {
            groupsMap.put(String.format("/team/%s", String.valueOf(team.getID())),
                    team.getName());
        }
        return groupsMap;
    }

    public ArrayList<Student> getStudentsList(Group group) {

        ArrayList<Student> students = new ArrayList<>();

        String query = "SELECT user_id FROM user_groups WHERE group_id = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {String.valueOf(group.getID())});
        SQLUsers sqlUsers = new SQLUsers();

        for (ArrayList<String> record : queryResult.subList(1, queryResult.size())) {
            User student = sqlUsers.getUserByID(Integer.parseInt(record.get(0)));
            students.add((Student) student);
        }
        return students;
    }

    public Boolean removeUserFromTeam(Team team, User student) {

        String query = "DELETE from user_groups WHERE user_id = ? AND group_id = ?;";
        return handleQuery(query, new String[] {String.valueOf(student.getID()), String.valueOf(team.getID())});
    }

    public Boolean addStudentToTeam(Team team, Student student) {

        String query = "INSERT INTO user_groups VALUES (?, ?);";
        return handleQuery(query, new String[] {String.valueOf(student.getID()), String.valueOf(team.getID())});
    }

    public Team createTeam() {

        String query = "INSERT INTO groups (name) VALUES (null);";
        handleQuery(query, null);

        query = "SELECT * FROM groups ORDER BY id DESC LIMIT 1;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);

        return getTeamById(Integer.parseInt(queryResult.get(1).get(0)));
    }

    public Boolean removeTeam(Team team) {

        for (User member : team.getMembers()) {
            removeUserFromTeam(team, member);
        }

        String query = "DELETE FROM groups WHERE id = ?;";

        return handleQuery(query, new String[] {String.valueOf(team.getID())});
    }

    public Boolean renameTeam(Team team, String newName) {

        String query = "UPDATE groups SET name = ? WHERE id = ?;";

        return handleQuery(query, new String[] {newName, String.valueOf(team.getID())});
    }


    public Boolean isInGroup(User user, Group group) {

        String query = "SELECT * FROM user_groups WHERE user_id = ? and group_id = ?;";
        return processQuery(query, new String[] {"" + user.getID(), "" + group.getID()}).size() > 1;
    }

    public ArrayList<Team> getUserTeams (Student user) {

        ArrayList<Team> teamList = new ArrayList<>();

        String query = "SELECT group_id FROM user_groups WHERE user_id = ?;";
        String[] data = new String[] {String.valueOf(user.getID())};

        ArrayList<ArrayList<String>> result = processQuery(query, data);

        for (ArrayList<String> record : result.subList(1, result.size())) {
            teamList.add(getTeamById(Integer.parseInt(record.get(0))));
        }
        return teamList;
    }
}