package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public interface MenuDAO {

    public LinkedHashMap<Integer,String> getMainMenu(User user);
    public LinkedHashMap<Integer,String> getBrowseMenu();
    public LinkedHashMap<Integer,String> getDetailsMenu();

}