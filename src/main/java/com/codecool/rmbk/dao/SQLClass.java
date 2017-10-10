package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.model.usr.Klass;

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
    public ArrayList<Student> getStudentsList(Group group) {

        String query = "SELECT * FROM users WHERE status = 'Student' AND class_name = ?;";
        return getStudents(group, query);
    }
}
