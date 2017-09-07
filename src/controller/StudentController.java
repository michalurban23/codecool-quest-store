package controller;

import model.usr.*;
import view.*;

public class StudentController extends UserController {

    public String getUserType() {

        return "Student";
    }

    public StudentController() {
        view = new ConsoleStudentView();
    }

    public void start (User user) {
        if (user.getClass().getSimpleName().equals("Student")) {
            setUser(user);
            handleStudentMenu();
        } else {
            handleMentorMenu();
        }
    }

    public void handleStudentMenu() {
        boolean isBrowsed = true;
        while (isBrowsed) {
            view.clearScrean();
            view.showShortInfo(user);
            Integer choice = view.handleStudentMenu();
            if (choice == 1) {
                editUserData(user);
            } else if (choice == 2) {
                QuestController questController = new QuestController();
                questController.start(user);
            } else if (choice == 3) {
                ShoppingController shoper = new ShoppingController();
                shoper.start(user);
            } else if (choice == 0) {
                isBrowsed = false;
            }
        }
    }

    public void handleMentorMenu() {

    }

}
