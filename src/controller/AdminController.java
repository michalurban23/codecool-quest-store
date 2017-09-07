package controller;

import model.usr.User;
import model.usr.Mentor;
import model.usr.Class;
import view.*;

public class AdminController extends UserController {

    ConsoleAdminView view;
    User user;

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
                ClassController classController = new ClassController();
                classController.start(user);
            } else if (choice == 3) {
                MentorController mentorController = new MentorController();
                mentorController.start(user);
            } else if (choice == 0) {
                isBrowsed = false;
            }
        }
    }

    public String getUserType() {
        return "Admin";
    }
}
