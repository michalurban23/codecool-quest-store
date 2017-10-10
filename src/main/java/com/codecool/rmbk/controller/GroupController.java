package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.*;
import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ConsoleGroupView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class GroupController {

    MenuDAO menuDAO;
    ConsoleGroupView view;
    TeamDAO groupDAO;
    UserInfoDAO userDAO;

    public GroupController () {
        menuDAO = new SQLMenuDAO();
        view = new ConsoleGroupView();
        userDAO = new SQLUsers();
    }

    public abstract void start (User user);

    public void editGroupName(Group team) {
        String newName = view.getNewGroupName();
        if (newName != null) {
            if (groupDAO.renameGroup(team, newName)) {
                team.setName(newName);
            }
        }
    }

    public void handleCRUDMenu(User user) {

        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            ArrayList<Group> groups = groupDAO.getTeamList(user);
            LinkedHashMap<Integer,String> menu = menuDAO.getBrowseMenu("edit");
            String choice = view.handleBrowse(menu, groups);

            if(choice.equals(menu.get(1))){
                Group chosenTeam = view.getListChoice(groups);

                if (chosenTeam != null) {
                    handleDetails(user, chosenTeam);
                }

            } else if(choice.equals(menu.get(2))) {
                Group newTeam = groupDAO.createGroup();
                editGroupName(newTeam);

            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    public void handleBrowseMenu(User user) {
        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            ArrayList<Team> groups = groupDAO.getTeamList(user);
            LinkedHashMap<Integer,String> menu = menuDAO.getBrowseMenu("show");
            String choice = view.handleBrowse(menu, groups);

            if(choice.equals(menu.get(1))){
                Team chosenTeam = view.getListChoice(groups);

                if (chosenTeam != null) {
                    handleDetails(user, chosenTeam);
                }

            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    protected abstract void handleDetails(User user, Group chosenTeam);

    void addUser(Group team) {
        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            groupDAO.updateMembers(team);
            ArrayList<ArrayList<String>> users = userDAO.getIdNameList("Student");
            LinkedHashMap<Integer,String> menu = menuDAO.getBrowseMenu("show");
            String choice = view.handleAddStudent(menu, users);

            if(choice.equals(menu.get(1))){
                ArrayList<String> chosenUserInfo = view.getListChoice(users);

                if (chosenUserInfo != null) {
                    Integer userId = Integer.parseInt(chosenUserInfo.get(0));
                    Student chosenStudent = (Student) userDAO.getUserByID(userId);
                    if (!groupDAO.isInGroup(chosenStudent, team)) {
                        groupDAO.addStudentToGroup(team, chosenStudent);
                        isBrowsed = false;
                    } else {
                        view.printMessage("Already in group!");
                    }
                }
            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    void handleMembership(Student student, Group team) {
        boolean isBrowsed = true;

        while(isBrowsed){
            view.clearScreen();
            LinkedHashMap<Integer,String> menu = menuDAO.getMemberMenu();
            String choice = view.MemberScreen(menu, student);

            if (choice.equals(menu.get(1))){
                isBrowsed = !groupDAO.removeStudentFromGroup(team, student);
            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }
}
