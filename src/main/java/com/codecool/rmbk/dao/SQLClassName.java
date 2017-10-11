package com.codecool.rmbk.dao;

public class SQLClassName extends SqlDAO {

    public void getAllClasses() {

        String query = "SELECT * FROM class_name";

        processQuery(query, null);
    }

    public void getClassName(int id) {

        String query = "SELECT * FROM class_name WHERE id = ?;";

        processQuery(query, new String[] {"" + id});
    }

}
