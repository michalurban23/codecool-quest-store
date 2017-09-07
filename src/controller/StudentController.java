package controller;

import model.usr.*;
import view.*;

public class StudentController extends UserController {

    ConsoleStudentView view;

    public String getUserType() {

        return "Student";
    }

    public StudentController() {

        this.view = new ConsoleStudentView();
        super.view = new ConsoleStudentView();
    }

    public void start (User student) {
        setUser(student);
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

}
