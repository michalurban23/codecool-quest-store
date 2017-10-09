package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLBacklog;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ConsoleBacklogView;
import com.codecool.rmbk.view.ConsoleView;

import java.util.TreeMap;

public class BacklogController {

    private ConsoleView display = new ConsoleBacklogView();
    private SQLBacklog backlog = new SQLBacklog();
    private TreeMap<Integer, String> menu = new TreeMap<>();
    private Boolean controllerRunning;
    private String accessLevel;

    void start(User user) {

        this.controllerRunning = true;

        if (user.getClass().getSimpleName().equals("Student")) {
            createStudentMenu();
            accessLevel = "Student";
        } else {
            createSupervisorMenu();
            accessLevel = "Supervisor";
        }

        runMenu();
    }

    private void runMenu() {

        while (controllerRunning) {
            display.showMenu("Backlog menu", menu);
            handleMenuChoice();
        }
    }

    private void handleMenuChoice() {

        String userChoice = display.getInput("Select an option: ");

        try {
            Integer choice = Integer.parseInt(userChoice);
            if (accessLevel.equals("Supervisor")) {
                handleSupevisorMenu(choice);
            } else {
                handleStudentMenu(choice);
            }
        } catch (NumberFormatException e) {
            display.printError("Invalid input! Try again.");
            display.pause();
        }
    }

    private void createStudentMenu() {

        menu.put(1, "d1");
        menu.put(2, "d2");
        menu.put(3, "d3");
    }

    private void createSupervisorMenu() {

        menu.put(1, "d1");
        menu.put(2, "D2");
        menu.put(3, "D3");
        menu.put(4, "d4");
    }

    private void handleStudentMenu(Integer choice) {

        switch (choice) {
            case 1:
                System.out.println("d1");
                break;
            case 2:
                System.out.println("d1");
                break;
            case 3:
                System.out.println("d1");
                break;
            case 0:
                stopController();
                break;
            default:
                display.printWarning("No such option available");
                display.pause();
        }
    }

    private void stopController() {

        controllerRunning = false;
    }
}
