package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.User;
import java.util.LinkedHashMap;

public interface UserView {

    public Integer handleMenu(LinkedHashMap<String,Integer> menu);
    public void showShortInfo(User user);
    public void showFullInfo(User user);
    public String[] getNewUserData();
}
