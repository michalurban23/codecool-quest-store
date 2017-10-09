package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ConsoleGroupView extends ConsoleView implements GroupView {

    @Override
    public String handleMenu(LinkedHashMap<Integer, String> menu) {
        ArrayList<String> options = new ArrayList<>(menu.values());
        showMenu(options);
        return getMenuChoice(options);
    }

    @Override
    public void showShortInfo(Group group) {
        showFullInfo(group);
    }

    @Override
    public void showFullInfo(Group group) {
        printMessage("Team:");
        printMessage(group.getName());
    }

    @Override
    public String getNewGroupName() {

        return getString("Enter new group name: ");
    }

    @Override
    public String MemberScreen(LinkedHashMap<Integer, String> menu, Student student) {
        printMessage(student.getFullName());
        return handleMenu(menu);
    }

    public String handleBrowse(LinkedHashMap<Integer,String> menu, ArrayList<ArrayList<String>> groups) {
        ArrayList<String> groupNames = new ArrayList<>();
        for (int i = 1; i < groups.size(); i++) {
            groupNames.add(groups.get(i).get(1));
        }
        showEnumeratedList(groupNames);
        System.out.println("\n");

        return handleMenu(menu);
    }

    public String handleDetails(LinkedHashMap<Integer,String> menu, Group group) {

        showShortInfo(group);
        System.out.println("\n");

        return handleMenu(menu);
    }
}
