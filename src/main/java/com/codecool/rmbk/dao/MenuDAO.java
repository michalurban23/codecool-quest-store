package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;
import java.util.LinkedHashMap;

public interface MenuDAO {

    LinkedHashMap<Integer,String> getMainMenu(User user);
    LinkedHashMap<Integer,String> getBrowseMenu(String option);
    LinkedHashMap<Integer,String> getDetailsMenu(String option);
    LinkedHashMap<Integer,String> getMemberMenu();
    LinkedHashMap<Integer,String> getTeamDetailsMenu(String show);
    LinkedHashMap<Integer,String> getClassDetailsMenu(User user);
}