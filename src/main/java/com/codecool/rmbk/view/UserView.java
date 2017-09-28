package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.User;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public interface UserView {

    public String handleMenu(LinkedHashMap<Integer,String> menu);
    public void showShortInfo(User user);
    public void showFullInfo(User user);
    public String[] getNewUserData();
}
