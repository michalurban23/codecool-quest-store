package controller;

import model.usr.Student;
import view.*;

public class StudentController extends UserController {

    public StudentController() {
        userView = new StudentConsoleView();
    }

    public void start (User student) {

        while (isBrowsed) {
            Integer choice = userView.handleMainMenu();
            if (choice == 1) {
                editUserData();
            } else if (choice == 2) {
                QuestController questController = new QuestController();
                questController.start(student);
            } else if (choice == 3) {
                ShoppingController shoper = new ShoppingController();
                shoper.start(student);
            } else if (choice == 0) {
                isBrowsed = false;
            }
        }
    }

}
