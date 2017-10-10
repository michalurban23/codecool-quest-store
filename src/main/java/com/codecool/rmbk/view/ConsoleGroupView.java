package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.Team;

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

    @Override
    public String handleAddStudent(LinkedHashMap<Integer, String> menu, ArrayList<ArrayList<String>> users) {
        ArrayList<String> userNames = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            userNames.add(users.get(i).get(1));
        }
        showEnumeratedList(userNames);
        System.out.println("\n");

        return handleMenu(menu);
    }

    public String handleBrowse(LinkedHashMap<Integer,String> menu, ArrayList<Team> groups) {
        ArrayList<String> groupNames = new ArrayList<>();
        for (Team group : groups) {
            groupNames.add(group.getName());
        }
        showEnumeratedList(groupNames);
        System.out.println("\n");

        return handleMenu(menu);
    }

    public String handleDetails(LinkedHashMap<Integer,String> menu, Group group) {

        showShortInfo(group);
        showEnumeratedList(group.getMembers());

        System.out.println("\n");

        return handleMenu(menu);
    }
}
