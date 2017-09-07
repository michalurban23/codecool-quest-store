package controller;

import model.usr.User;
import model.usr.Mentor;
import model.usr.Class;
import view.*;

public class AdminController extends UserController {

    ConsoleAdminView view;

    public AdminController() {
        view = new ConsoleAdminView();
    }

    public void start (User admin) {
        setUser(admin);
        handleMainMenu();
    }

    public void handleMainMenu() {
        boolean isBrowsed = true;
        while (isBrowsed) {
            view.clearScrean();
            view.showShortInfo(user);
            Integer choice = view.handleMainMenu();
            if (choice == 1) {
                editUserData(user);
            } else if (choice == 2) {
                GroupController groupController = new GroupController();
                groupController.start(user);
            } else if (choice == 3) {
                MentorController mentorController = new MentorController();
                mentorController.start(user);
            } else if (choice == 0) {
                isBrowsed = false;
            }
        }
    }

    public void createClass() {
        String className = view.showCreatingClass();
        if (className != null) {
            Class newClass = new Class();
            newClass.setName(className);
        }
    }

    public User createNewMentor() {
        String[] info = view.getNewUserData();
        return new Mentor();
    }

    public void removeMentor(Mentor mentor) {;}

    public String getUserType() {
        return "Admin";
    }
}
