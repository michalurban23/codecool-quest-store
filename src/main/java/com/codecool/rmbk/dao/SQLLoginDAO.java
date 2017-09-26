package com.codecool.rmbk.dao;

import java.sql.*;

public class SQLLoginDAO extends SQLDAO implements LoginDAO {

    private ResultSet results;

    public void start() {;}

    public Boolean login(String[] loginInfo) {

        String user_name = loginInfo[0];
        String user_pass = loginInfo[1];
        String password;

        String query = "SELECT password, login FROM 'login_info' WHERE login = '" + user_name + "';";
        try {
            results = getData(query);
            password = results.getString("login");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            password = "";
        }
        System.out.println(password);
        System.out.println(user_pass);
        System.out.println(user_name);
        System.out.println(password.equals(user_pass));
        return password.equals(user_pass);
    }

}