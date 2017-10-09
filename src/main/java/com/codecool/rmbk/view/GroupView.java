package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.Group;

import java.util.LinkedHashMap;

public interface GroupView {

    public String handleMenu(LinkedHashMap<Integer, String> menu);

    public void showShortInfo(Group user);

    public void showFullInfo(Group user);

    public String getNewGroupName();
}
