package com.codecool.rmbk.dao;

import java.util.ArrayList;
import com.codecool.rmbk.model.usr.User;

public class SQLUsers extends SqlDAO implements UserInfoDAO{

    private ArrayList<ArrayList<String>> results;

    public void getAllUsers() {
        String query = "SELECT * FROM users";

        processQuery(query);
    }

    public void getUser(int id) {
        String query = "SELECT * from users WHERE id = '" + id + "';";

        processQuery(query);
    }

    @Override
    public User getUserByLogin(String login){

        return null;
//        String query = String.format("SELECT * from users WHERE login ='%s';", login);
//        ArrayList<ArrayList<String>> result;
//        result = processQuery(query);
//        Class.forName("Student")(result.get(0).toArray());
    }

    @Override
    public String getUserTypeByLogin(String login) {
        return null;
    }

    @Override
    public ArrayList<String> getNameList(String userType) {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User getUserByID(Integer id) {
        return null;
    }

    @Override
    public ArrayList<User> getUserList(String userType) {
        return null;
    }

    @Override
    public Boolean deleteUser(User user) {
        return null;
    }

    @Override
    public Boolean updateUserName(User user, String name) {
        return null;
    }

    @Override
    public Boolean updateUserSurname(User user, String surname) {
        return null;
    }

    @Override
    public Boolean updateUserEmail(User user, String email) {
        return null;
    }

    @Override
    public Boolean updateUserAddress(User user, String adress) {
        return null;
    }
}

