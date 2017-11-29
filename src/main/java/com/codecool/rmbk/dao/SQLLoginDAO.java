package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.PasswordHash;
import com.codecool.rmbk.model.usr.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SQLLoginDAO extends SqlDAO {

    public Boolean login(String loginName, String loginPass) {

        String password = "";
        String salt = "";

        String query = "SELECT password, login, salt FROM 'login_info' WHERE login = ?;";

        handleQuery(query, new String[] {loginName});
        ArrayList<ArrayList<String>> results = getResults();

        if (results.size() > 1) {
            password = results.get(1).get(0);
            salt = results.get(1).get(2);
        }
        String hash = PasswordHash.hash(loginPass, salt);
        return password.equals(hash);
    }

    public static Boolean setPermission() {

        File file = new File("src/main/resources/queststore.db");
        return file.setWritable(true, false);
    }

    public Map<String, String> getCredentialsMap() {

        return new TreeMap<>();// TODO
    }

    public void updateCredentials(User user, Map<String, String> data) {

        // TODO
    }

}