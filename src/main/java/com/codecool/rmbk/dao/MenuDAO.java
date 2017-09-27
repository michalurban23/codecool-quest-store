package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;

import java.util.TreeMap;

public interface MenuDAO {

    public TreeMap<Integer, String> getMainMenu(User user);
}
