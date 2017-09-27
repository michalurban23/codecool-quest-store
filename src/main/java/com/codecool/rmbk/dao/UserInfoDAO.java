package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public interface UserInfoDAO {

    public User getUserByLogin(String login);
    public String getUserTypeByLogin(String login);
    public User getUserByName (String name);
    public User getUserByID(Integer id);
    public ArrayList<User> getUserList(String userType);
    public ArrayList<String> getNameList(String userType); // returns List<String>: name + surname
    public Boolean deleteUser(User user); // returns true if successfully finished
    public Boolean updateUserName(User user, String name); // returns true if successfully finished
    public Boolean updateUserSurname(User user, String surname); // returns true if successfully finished
    public Boolean updateUserEmail(User user, String email); // returns true if successfully finished
    public Boolean updateUserAddress(User user, String adress); // returns true if successfully finished
}
