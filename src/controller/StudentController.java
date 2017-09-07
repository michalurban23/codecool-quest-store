package controller;

import model.usr.*;
import view.*;

public class StudentController extends UserController {

    public StudentController() {
        userView = new ConsoleStudentView();
    }

    public void start (User student) {

        boolean isBrowsed = true;
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
