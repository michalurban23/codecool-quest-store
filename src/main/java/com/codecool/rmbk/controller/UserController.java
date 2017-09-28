package com.codecool.rmbk.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import com.codecool.rmbk.dao.MenuDAO;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.dao.UserInfoDAO;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ConsoleUserView;

public abstract class UserController {

    User user;
    ConsoleUserView view;
    UserInfoDAO userDao;
    MenuDAO menuDao;

    public UserController() {
        view = new ConsoleUserView();
        userDao = new SQLUsers();
        menuDao = new SQLMenuDAO();
    }

    public void setUser(User user) {

        this.user = user;
    }

    public void start (User user) {

        if (user.getClass().getSimpleName().equals(getUserType())) {
            setUser(user);
            handleMainMenu();
        } else {
            handleSupervisorMenu(getUserType());
        }
    }

    public void editUserData(User user) {
        String[] newData = view.getNewUserData();

        if (newData[0] != null) {
            user.setFirstName(newData[0]);
        }
        if (newData[1] != null) {
            user.setLastName(newData[1]);
        }
        if (newData[2] != null) {
            user.setEmail(newData[2]);
        }
        if (newData[3] != null) {
            user.setAddress(newData[3]);
        }
        userDao.updateUser(user);
    }

    public void handleMainMenu() {

        boolean isBrowsed = true;

        while(isBrowsed){
            view.clearScreen();
            view.showShortInfo(user);
            LinkedHashMap<Integer,String> menu = menuDao.getMainMenu(user);
            String choice = view.handleMenu(menu);

            if(choice.equals(menu.get(1))){
                view.showFullInfo(user);
                editUserData(user);
            } else if(choice.equals(menu.get(2))) {
                ClassController classController = new ClassController();
                classController.start(user);
            } else if(choice.equals(menu.get(3))) {
                StudentController studentController = new StudentController();
                studentController.start(user);
            } else if(choice.equals(menu.get(4))) {
                MentorController mentorController = new MentorController();
                mentorController.start(user);
            } else if(choice.equals(menu.get(5))) {
                GroupController groupController = new GroupController();
                groupController.start(user);
            } else if(choice.equals(menu.get(6))) {
                QuestController questController = new QuestController();
                questController.start(user);
            } else if(choice.equals(menu.get(7))) {
                ArtifactController artifactController = new ArtifactController();
                artifactController.start(user);
            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    private void handleSupervisorMenu(String userType) {

        boolean isBrowsed = true;

        while(isBrowsed) {

            view.clearScreen();
            ArrayList<ArrayList<String>> users = userDao.getIdNameList(userType);
            LinkedHashMap<Integer,String> menu = menuDao.getBrowseMenu();
            String choice = view.handleBrowse(menu, users);

            if(choice.equals(menu.get(1))){
                ArrayList<String> chosenUserInfo = view.getListChoice(users);

                if (chosenUserInfo != null) {
                    Integer userId = Integer.parseInt(chosenUserInfo.get(0));
                    handleDetailsMenu(userDao.getUserByID(userId));
                }

            } else if(choice.equals(menu.get(2))) {
                User newUser = userDao.addUser(userType);
                editUserData(newUser);

            } else if(choice.equals(menu.get(0))) {
                isBrowsed = false;
            }
        }
    }

    private void handleDetailsMenu(User user) {

        boolean isBrowsed = true;

        while(isBrowsed){
            view.clearScreen();
            LinkedHashMap<Integer,String> menu = menuDao.getDetailsMenu();
            String choice = view.handleDetails(menu, user);

            if (choice.equals(menu.get(1))){
                editUserData(user);
            } else if(choice.equals(menu.get(2))){
                isBrowsed = !userDao.removeUser(user);
            } else if(choice.equals(menu.get(0))){
                isBrowsed = false;
            }
        }
    }

    public abstract String getUserType();

}
