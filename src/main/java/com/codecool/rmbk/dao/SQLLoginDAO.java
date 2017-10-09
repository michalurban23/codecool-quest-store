package com.codecool.rmbk.dao;

import java.io.File;
import java.util.ArrayList;

public class SQLLoginDAO extends SqlDAO implements LoginDAO {

    private ArrayList<ArrayList<String>> results;

    public Boolean login(String[] loginInfo) {

        String user_name = loginInfo[0];
        String user_pass = loginInfo[1];
        String password = "";
        String salt = "";

        String query = "SELECT password, login, salt FROM 'login_info' WHERE login = ?;";

        handleQuery(query, new String[] {user_name});
        results = getResults();

        if (results.size() > 1) {
            password = results.get(1).get(0);
            salt = results.get(1).get(2);
        }
        String hash = PasswordHash.hash(user_pass, salt);
        return password.equals(hash);
    }

    public static Boolean setPermission() {

        File file = new File("src/main/resources/queststore.db");
        return file.setWritable(true, false);
    }
}