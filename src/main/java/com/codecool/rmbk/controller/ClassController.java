package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLClass;
import com.codecool.rmbk.dao.SQLTeam;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.model.usr.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ClassController extends GroupController {

    public ClassController() {
        super();
        groupDAO = new SQLClass();
    }

    public void start (User user) {
        if (user.getClass().getSimpleName().equals("Admin")) {
            handleCRUDMenu(user);
        } else {
            handleBrowseMenu(user);
        }
    }

    @Override
    public void handleDetails(User user, Group team) {

        boolean isBrowsed = true;

        while (isBrowsed) {
            view.clearScreen();
            groupDAO.updateMembers(team);
            LinkedHashMap<Integer, String> menu = menuDAO.getClassDetailsMenu(user);

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
                assignMentor(team);
            } else if (choice.equals(menu.get(5))) {
                isBrowsed = !groupDAO.removeGroup(team);
            } else if (choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    private void assignMentor(Group team) {
        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            groupDAO.updateMembers(team);
            ArrayList<ArrayList<String>> mentors = userDAO.getIdNameList("Mentor");
            LinkedHashMap<Integer,String> menu = menuDAO.getBrowseMenu("show");
            String choice = view.handleAddStudent(menu, mentors);

            if(choice.equals(menu.get(1))){
                ArrayList<String> chosenUserInfo = view.getListChoice(mentors);

                if (chosenUserInfo != null) {
                    Integer userId = Integer.parseInt(chosenUserInfo.get(0));
                    Mentor chosenMentor = (Mentor) userDAO.getUserByID(userId);
                    if (!groupDAO.isInGroup(chosenMentor, team)) {
                        groupDAO.addUserToGroup(team, chosenMentor);
                        isBrowsed = false;
                    } else {
                        view.printMessage("Already assigned!");
                    }

                }
            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }
}
