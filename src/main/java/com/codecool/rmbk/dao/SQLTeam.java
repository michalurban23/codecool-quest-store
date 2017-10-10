package com.codecool.rmbk.dao;


import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public class SQLTeam extends SQLGroups {

    private ArrayList<ArrayList<String>> results;

    public SQLTeam() {

        this.tableName = "groups";
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
    public ArrayList<Group> getGroupList(User user) {

        String query;
        String[] stringSet = null;
        ArrayList<Group> result = new ArrayList<>();

        if(user.getClass().getSimpleName().equals("Mentor")) {
            query = "SELECT * FROM " + tableName + ";";

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

        String query = "SELECT * FROM " + tableName +
                       " INNER JOIN user_groups ON user_groups.user_id = users.id " +
                       "WHERE user_groups.group_id = ?;";
        return getStudents(group, query);
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

        ArrayList<Student> result = new ArrayList<>();
        SQLUsers sqlUsers = new SQLUsers();
        team.clearMembersList();

        for(ArrayList<String> ar : queryResult.subList(1, queryResult.size())) {
            team.addMember((Student) sqlUsers.getUserByID(Integer.parseInt(ar.get(0))));
        }
    }
}