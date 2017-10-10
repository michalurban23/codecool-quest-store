package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.*;
import com.codecool.rmbk.model.usr.Mentor;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ConsoleGroupView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TeamController {

    MenuDAO menuDAO;
    ConsoleGroupView view;
    TeamDAO groupDAO;
    UserInfoDAO userDAO;

    public TeamController() {
        menuDAO = new SQLMenuDAO();
        view = new ConsoleGroupView();
        groupDAO = new SQLTeam();
        userDAO = new SQLUsers();
    }

    public void start (User user) {
        if (user.getClass().getSimpleName().equals("Mentor")) {
            handleCRUDMenu((Mentor) user);
        } else {
            handleBrowseMenu((Student) user);
        }
    }


    public void editGroupName(Team team) {
        String newName = view.getNewGroupName();
        if (newName != null) {
            if (groupDAO.renameGroup(team, newName)) {
                team.setName(newName);
            }
        }
    }

    public void handleCRUDMenu(Mentor user) {

        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            ArrayList<Team> groups = groupDAO.getTeamList(user);
            LinkedHashMap<Integer,String> menu = menuDAO.getBrowseMenu("edit");
            String choice = view.handleBrowse(menu, groups);

            if(choice.equals(menu.get(1))){
                Team chosenTeam = view.getListChoice(groups);

                if (chosenTeam != null) {
                    handleEditDetailsMenu(user, chosenTeam);
                }

            } else if(choice.equals(menu.get(2))) {
                Team newTeam = groupDAO.createGroup();
                editGroupName(newTeam);

            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    public void handleBrowseMenu(Student user) {
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

    private void handleEditDetailsMenu(Mentor user, Team team) {

        boolean isBrowsed = true;

        while (isBrowsed) {
            view.clearScreen();
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

    private void addUser(Team team) {
        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            ArrayList<ArrayList<String>> users = userDAO.getIdNameList("Student");
            LinkedHashMap<Integer,String> menu = menuDAO.getBrowseMenu("show");
            String choice = view.handleAddStudent(menu, users);

            if(choice.equals(menu.get(1))){
                ArrayList<String> chosenUserInfo = view.getListChoice(users);

                if (chosenUserInfo != null) {
                    Integer userId = Integer.parseInt(chosenUserInfo.get(0));
                    groupDAO.addStudentToGroup(team, userDAO.getUserByID(userId));
                }

            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    private void handleMembership(Student student, Team team) {
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

    private void handleShowDetailsMenu(Team team) {

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
