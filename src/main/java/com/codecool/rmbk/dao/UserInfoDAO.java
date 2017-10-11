package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;
import java.util.Map;

public interface UserInfoDAO {

    User getUserByLogin(String login);
    String getUserTypeByLogin(String login);
    User getUserByName (String name);
    User getUserByID(Integer id);
    ArrayList<User> getUserList(String userType);
    ArrayList<ArrayList<String>> getIdNameList(String userType); // returns List<String>: name + surname
    Boolean removeUser(User user); // returns true if successfully finished
    Boolean updateUser(User user); // returns true if successfully finished
    User addUser(String userType);
}