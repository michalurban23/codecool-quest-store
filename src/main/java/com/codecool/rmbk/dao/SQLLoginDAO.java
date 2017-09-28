package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLLoginDAO extends SqlDAO implements LoginDAO {

    private ArrayList<ArrayList<String>> results;

    public Boolean login(String[] loginInfo) {

        String user_name = loginInfo[0];
        String user_pass = loginInfo[1];
        String password = "";

        String query = "SELECT password, login FROM 'login_info' WHERE login = '" + user_name + "';";

        handleQuery(query);
        results = getResults();

        if (results.size() > 1) {
            password = results.get(1).get(0);
        }
        return password.equals(user_pass);
    }

}