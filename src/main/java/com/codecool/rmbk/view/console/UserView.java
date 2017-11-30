package com.codecool.rmbk.view.console;

import com.codecool.rmbk.model.usr.User;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public interface UserView {

    String handleMenu(LinkedHashMap<Integer,String> menu);
    void showShortInfo(User user);
    void showFullInfo(User user);
    String[] getNewUserData();
}
