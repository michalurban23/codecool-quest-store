package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.Class;
import com.codecool.rmbk.model.usr.Mentor;

import java.util.ArrayList;

public class SQLClassName extends SqlDAO {

    private ArrayList<ArrayList<String>> results;

    public void getAllClasses() {
        String query = "SELECT * FROM class_name";

        processQuery(query, null);
    }

    public String getClassName(int id) {
        String query = "SELECT * FROM class_name WHERE id = ?;";

        return processQuery(query, new String[] {"" + id}).get(1).get(1);
    }

    public void updateClassName(Class cls, String newName) {

        String query = "UPDATE class_name SET name = ? WHERE id = ?;";
        processQuery(query, new String[] {newName, "" + cls.getId()});
    }

    public void addMentorToClass(Mentor user, Class cls) {

        String query = "UPDATE users SET class_name = ? WHERE id = ?;";
        processQuery(query, new String[] {cls.getName(), "" + user.getID()});
    }

    public void removeMentorFromClass(Mentor user) {

        String query = "UPDATE users SET class_name = null where id = ?;";
        processQuery(query, new String[] {"" + user.getID()});
    }

    


}
