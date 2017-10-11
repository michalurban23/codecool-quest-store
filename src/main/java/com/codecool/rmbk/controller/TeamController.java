package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.*;
import com.codecool.rmbk.model.usr.*;
import com.codecool.rmbk.view.ConsoleGroupView;
import java.util.ArrayList;
import java.util.LinkedHashMap;

class TeamController {

    private MenuDAO menuDAO;
    private ConsoleGroupView view;
    private TeamDAO groupDAO;
    private UserInfoDAO userDAO;

    TeamController() {

        menuDAO = new SQLMenuDAO();
        view = new ConsoleGroupView();
        groupDAO = new SQLTeam();
        userDAO = new SQLUsers();
    }

    void start(User user) {

        if (user.getClass().getSimpleName().equals("Mentor")) {
            handleCRUDMenu(user);
        } else {
            handleBrowseMenu(user);
        }
    }

    private void editGroupName(Group team) {

        String newName = view.getNewGroupName();

        if (newName != null) {
            if (groupDAO.renameGroup(team, newName)) {
                team.setName(newName);
            }
        }
    }

    private void handleCRUDMenu(User user) {

        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            ArrayList<Team> groups = groupDAO.getTeamList(user);
            LinkedHashMap<Integer,String> menu = menuDAO.getBrowseMenu("edit");
            String choice = view.handleBrowse(menu, groups);

            if(choice.equals(menu.get(1))){
                Team chosenTeam = view.getListChoice(groups);

                if (chosenTeam != null) {
                    handleEditDetailsMenu(chosenTeam);
                }

            } else if(choice.equals(menu.get(2))) {
                Group newTeam = groupDAO.createGroup();
                editGroupName(newTeam);

            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    private void handleBrowseMenu(User user) {

        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            ArrayList<Team> groups = groupDAO.getTeamList(user);
            LinkedHashMap<Integer,String> menu = menuDAO.getBrowseMenu("show");
            String choice = view.handleBrowse(menu, groups);

            if(choice.equals(menu.get(1))){
                Team chosenTeam = view.getListChoice(groups);

                if (chosenTeam != null) {
                    handleShowDetailsMenu(chosenTeam);
                }

            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    private void handleEditDetailsMenu(Group team) {

        boolean isBrowsed = true;

        while (isBrowsed) {

            view.clearScreen();
            groupDAO.updateMembers(team);
            LinkedHashMap<Integer, String> menu = menuDAO.getTeamDetailsMenu("edit");

            String choice = view.handleDetails(menu, team);

            if (choice.equals(menu.get(1))) {
                editGroupName(team);
            } else if (choice.equals(menu.get(2))) {

                Student student = view.getListChoice(groupDAO.getStudentsList(team));

                if (student != null) {
                    handleMembership(student, team);
                }
            } else if (choice.equals(menu.get(3))) {
                addUser(team);
            } else if (choice.equals(menu.get(4))) {
                isBrowsed = !groupDAO.removeTeam(team);
            } else if (choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    private void addUser(Group team) {

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

    private void handleMembership(Student student, Group team) {

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

    private void handleShowDetailsMenu(Group team) {

        boolean isBrowsed = true;

        while(isBrowsed){
            view.clearScreen();
            LinkedHashMap<Integer,String> menu = menuDAO.getTeamDetailsMenu("show");
            String choice = view.handleDetails(menu, team);

            if(choice.equals(menu.get(0))){
                isBrowsed = false;
            }
        }
    }

}
