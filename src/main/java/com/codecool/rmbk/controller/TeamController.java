package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.*;
import com.codecool.rmbk.model.usr.*;
import java.util.LinkedHashMap;

public class TeamController extends GroupController {

    TeamController() {

        super();
        groupDAO = new SQLTeam();
    }

    public void start(User user) {

        if (user.getClass().getSimpleName().equals("Mentor")) {
            handleCRUDMenu(user);
        } else {
            handleBrowseMenu(user);
        }
    }

    public void handleDetails(User user, Group team) {
        if (user.getClass().getSimpleName().equals("Mentor")) {
            handleEditDetailsMenu(team);
        } else {
            handleShowDetailsMenu(team);
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
                Student student = (Student) view.getListChoice(groupDAO.getStudentsList(team));

                if (student != null) {
                    handleMembership(student, team);
                }
            } else if (choice.equals(menu.get(3))) {
                addUser(team);
            } else if (choice.equals(menu.get(4))) {
                isBrowsed = !groupDAO.removeGroup(team);
            } else if (choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

}
