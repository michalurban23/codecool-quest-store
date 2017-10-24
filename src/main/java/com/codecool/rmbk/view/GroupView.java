package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface GroupView {

    String handleMenu(LinkedHashMap<Integer, String> menu);

    void showShortInfo(Group user);

    void showFullInfo(Group user);

    String getNewGroupName();

    String MemberScreen(LinkedHashMap<Integer, String> menu, Student student);

    String handleAddStudent(LinkedHashMap<Integer, String> menu, ArrayList<ArrayList<String>> users);
}
