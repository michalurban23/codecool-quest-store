package com.codecool.rmbk.dao;

import java.sql.*;

public class SQLLoginDAO extends Login implements LoginDAO {

    public void start() {;}

    public Boolean login(String[] loginInfo) {;}

    private String[] logIn() {

        while (!checkPassword()) {
            view.showWrongDataMessage();
            loginInfo = view.LoginScreen();
        }
        return loginInfo;
    }


    private boolean checkPassword() {

        if (!checkLogin(loginInfo[0])) {
            return false;
        } else if (!loginDatabase.get(loginInfo[0]).equals(loginInfo[1])) {
            return false;
        } else {
            return true;
        }
    }

}
