package com.codecool.rmbk.controller;


public class StudentController extends UserController {

    public String getUserType() {
        return "Student";
    }

    public StudentController() {
<<<<<<< HEAD

        view = new ConsoleStudentView();
        super.view = view;
    }

    public void start (User user) {

        if (user.getClass().getSimpleName().equals("Student")) {
            setUser(user);
            handleStudentMenu();
        } else {
            handleSupervisorMenu();
        }
    }

    public void handleStudentMenu() {

        boolean isBrowsed = true;

        while (isBrowsed) {
            view.clearScreen();
            view.showShortInfo(user);
            Integer choice = view.handleMainMenu();

            if (choice == 1) {
                view.showFullInfo(user);
                editUserData(user);
            } else if (choice == 2) {
                ShoppingController shoper = new ShoppingController(user);
                shoper.startShoppingController();
            } else if (choice == 0) {
                isBrowsed = false;
            }
        }
=======
        super();
>>>>>>> dev
    }
}
