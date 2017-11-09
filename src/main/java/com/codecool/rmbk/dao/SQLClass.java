package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLClass extends SQLGroups {

    public SQLClass() {

        this.tableName = "class_name";
    }

    @Override
    public ArrayList<Group> getGroupList(User user) {

        String query = "SELECT * FROM " + tableName + ";";
        ArrayList<Group> result = new ArrayList<>();
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);

        for(ArrayList<String> arr : queryResult.subList(1, queryResult.size())) {
            result.add(new Klass(Integer.parseInt(arr.get(0)), arr.get(1)));
        }
        return result;
    }

    @Override
    public ArrayList<User> getStudentsList(Group group) {

        String query = "SELECT * FROM users WHERE status = 'Student' AND " +
                       "class_name = (SELECT name FROM " + tableName + " WHERE id = ?);";
        return getUsers(group, query);
    }

    public ArrayList<User> getMentorsList(Group group) {

        String query = "SELECT * FROM users WHERE status = 'Mentor' AND " +
                "class_name = (SELECT name FROM " + tableName + " WHERE id = ?);";
        return getUsers(group, query);
    }

    public Boolean isInGroup(User user, Group group) {

        String query = "SELECT * FROM users WHERE id = ? AND class_name = ?;";
        return processQuery(query, new String[] {"" + user.getID(), group.getName()}).size() > 1;
    }

    @Override
    public void updateMembers(Group group) {

        String query = "SELECT * FROM users " +
                "INNER JOIN user_groups ON user_groups.user_id = users.id " +
                "WHERE user_groups.group_id = ?;";
        super.updateUsers(group, query);
    }

    @Override
    public Boolean removeStudentFromGroup(Group group, Student student) {

        String query = "UPDATE users SET class_name = null WHERE id = ?;";
        return handleQuery(query, new String[] {"" + student.getID()});
    }

    public void addUserToGroup(Group group, User user) {

        String query = "UPDATE users SET class_name = ? WHERE id = ?;";
        handleQuery(query, new String[] {group.getName(), "" + user.getID()});
    }


}
