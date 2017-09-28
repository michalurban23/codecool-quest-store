package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public interface UserInfoDAO {

    public User getUserByLogin(String login);
    public String getUserTypeByLogin(String login);
    public User getUserByName (String name);
    public User getUserByID(Integer id);
    public ArrayList<User> getUserList(String userType);
    public ArrayList<ArrayList<String>> getIdNameList(String userType); // returns List<String>: name + surname
    public Boolean removeUser(User user); // returns true if successfully finished
    public Boolean updateUser(User user); // returns true if successfully finished
    public User addUser(String userType);
}