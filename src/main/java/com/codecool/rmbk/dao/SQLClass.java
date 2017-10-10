package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.*;

import java.util.ArrayList;

public class SQLClass extends SQLGroups {

    private ArrayList<ArrayList<String>> results;

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
                       "class_name = (SELECT name FROM class_name WHERE id = ?);";
        return getUsers(group, query);
    }

    public ArrayList<User> getMentorsList(Group group) {

        String query = "SELECT * FROM users WHERE status = 'Mentor' AND " +
                "class_name = (SELECT name FROM class_name WHERE id = ?);";
        return getUsers(group, query);
    }

    public Boolean isInGroup(User user, Group group) {

        String query = "SELECT * FROM users WHERE id = ? AND class_name = ?;";
        return processQuery(query, new String[] {"" + user.getID(), group.getName()}).size() > 1;
    }
}
