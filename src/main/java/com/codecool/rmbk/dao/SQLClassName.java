package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.Class;
import com.codecool.rmbk.model.usr.Mentor;
import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public class SQLClassName extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public void getAllClasses() {

        String query = "SELECT * FROM class_name";
        processQuery(query, null);
    }

    public Class getClassById(int id) {

        String query = "SELECT * FROM class_name WHERE id = ?;";
        ArrayList<String> queryResult =  processQuery(query, new String[] {"" + id}).get(1);
        return new Class(queryResult.get(0), queryResult.get(1));
    }

    public Class createNewClass(String newName) {

        String query = "INSERT INTO class_name (name) values(null);";
        handleQuery(query, null);
        query = "SELECT * FROM class_name ORDER BY id DESC LIMIT 1;";
        ArrayList<String> queryResult = processQuery(query, null).get(1);
        return getClassById(Integer.parseInt(queryResult.get(1)));
    }

    public void updateClassName(Class cls, String newName) {

        String query = "UPDATE class_name SET name = ? WHERE id = ?;";
        processQuery(query, new String[] {newName, "" + cls.getId()});
    }

    public void addUserToClass(User user, Class cls) {

        String query = "UPDATE users SET class_name = ? WHERE id = ?;";
        processQuery(query, new String[] {cls.getName(), "" + user.getID()});
    }

    public void removeUserFromClass(User user) {

        String query = "UPDATE users SET class_name = null where id = ?;";
        processQuery(query, new String[] {"" + user.getID()});
    }

}
