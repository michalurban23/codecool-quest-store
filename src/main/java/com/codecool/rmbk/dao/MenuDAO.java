package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;

import java.util.LinkedHashMap;

public interface MenuDAO {

    public LinkedHashMap<Integer,String> getMainMenu(User user);
    public LinkedHashMap<Integer,String> getBrowseMenu(String option);
    public LinkedHashMap<Integer,String> getDetailsMenu(String option);
    public LinkedHashMap<Integer,String> getMemberMenu();
}