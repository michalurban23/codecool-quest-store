package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.MenuDAO;
import com.codecool.rmbk.dao.SQLGroups;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ConsoleGroupView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TeamController {

    MenuDAO menuDAO;
    ConsoleGroupView view;
    TeamDAO groupDAO;

    public TeamController() {
        menuDAO = new SQLMenuDAO();
        view = new ConsoleGroupView();
        groupDAO = new SQLTeam();
    }

    public void start (User user) {
        handleBrowseMenu(user);
    }

    public void editGroupName(Team team) {
        String newName = view.getNewGroupName();
        if (newName != null) {
            if (groupDAO.renameGroup(team, newName)) {
                team.setName(newName);
            }
        }
    }

    public void handleBrowseMenu(User user) {
        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            ArrayList<ArrayList<String>> groups = groupDAO.getTeamList(user);
            LinkedHashMap<Integer,String> menu = menuDAO.getBrowseMenu();
            String choice = view.handleBrowse(menu, groups);

            if(choice.equals(menu.get(1))){
                ArrayList<String> chosenRow = view.getListChoice(groups);

                if (chosenRow != null) {
                    Integer id = Integer.parseInt(chosenRow.get(0));
                    handleDetailsMenu(groupDAO.getTeamById(id));
                }

            } else if(choice.equals(menu.get(2))) {
                Team newTeam = groupDAO.createGroup();
                editGroupName(newTeam);

            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    private void handleDetailsMenu(Team team) {

        boolean isBrowsed = true;

        while(isBrowsed){
            view.clearScreen();
            LinkedHashMap<Integer,String> menu = menuDAO.getDetailsMenu();
            String choice = view.handleDetails(menu, team);

            if (choice.equals(menu.get(1))){
                editGroupName(team);
            } else if(choice.equals(menu.get(2))){
                isBrowsed = !groupDAO.removeTeam(team);
            } else if(choice.equals(menu.get(0))){
                isBrowsed = false;
            }
        }
    }

}
