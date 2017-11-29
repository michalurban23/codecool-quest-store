package com.codecool.rmbk.dao;

import com.codecool.rmbk.helper.PasswordHash;
import com.codecool.rmbk.model.usr.User;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> getCredentialsMap(Integer id) {

        String query = "SELECT login, password, salt " +
                "FROM 'login_info' " +
                "WHERE id = ?;";

        processQuery(query, new String[] {id.toString()});

        ArrayList<String> hashedCredentials = getResults().get(1);
        Map<String, String> credentials = new HashMap<>();

        credentials.put("login", hashedCredentials.get(0));
        credentials.put("password", hashedCredentials.get(1));
        credentials.put("salt", hashedCredentials.get(2));

        return credentials;
    }

    public void updateCredentials(User user, Map<String, String> data) {

        String id = String.valueOf(user.getID());
        String login = data.get("login");
        String salt = PasswordHash.getSalt();
        String password = PasswordHash.hash(data.get("newpass1"), salt);

        String query = "UPDATE login_info " +
                "SET login = ?, password = ?, salt = ? " +
                "WHERE id = ?;";

        processQuery(query, new String[] {login, password, salt, id});


    }

    public void updateLogin(User user, Map<String, String> data) {

        String id = String.valueOf(user.getID());
        String login = data.get("login");

        String query = "UPDATE login_info " +
                "SET login = ? " +
                "WHERE id = ?;";

        processQuery(query, new String[] {login, id});
    }
}