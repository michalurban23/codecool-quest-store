package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLBacklog;
import com.codecool.rmbk.dao.SQLExperience;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ConsoleBacklogView;
import com.codecool.rmbk.view.ConsoleView;
import java.util.ArrayList;
import java.util.TreeMap;

class BacklogController {

    private ConsoleView display = new ConsoleBacklogView();
    private SQLBacklog backlogDao = new SQLBacklog();
    private SQLExperience experienceDao = new SQLExperience();
    private TreeMap<Integer, String> menu = new TreeMap<>();
    private Boolean controllerRunning;
    private String accessLevel;
    private User user;

    void start(User user) {

        this.controllerRunning = true;
        this.user = user;

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
                handleSupervisorMenu(choice);
            } else {
                handleStudentMenu(choice);
            }
        } catch (NumberFormatException e) {
            display.printError("Invalid input! Try again.");
            display.pause();
        }
    }

    private void createStudentMenu() {

        menu.put(1, "Show my history");
        menu.put(2, "Show experience levels");
    }

    private void createSupervisorMenu() {

        menu.put(1, "Show my history");
        menu.put(2, "Show students history");
    }

    private void handleStudentMenu(Integer choice) {

        switch (choice) {
            case 1:
                showMyHistory();
                break;
            case 2:
                showExperienceLevels();
                break;
            case 0:
                stopController();
                break;
            default:
                display.printWarning("No such option available");
                display.pause();
        }
    }

    private void handleSupervisorMenu(Integer choice) {

        switch (choice) {
            case 1:
                showMyHistory();
                break;
            case 2:
                showAllHistory();
                break;
            case 0:
                stopController();
                break;
            default:
                display.printWarning("No such option available");
                display.pause();
        }
    }

    private void showMyHistory() {

        String title = "Personal history";
        ArrayList<ArrayList<String>> history = backlogDao.getBacklog(user.getID());

        display.printList(title, history);
    }

    private void showAllHistory() {

        String title = "All students history";
        ArrayList<ArrayList<String>> history = backlogDao.getAllBacklogs();

        display.printList(title, history);
    }

    private void showExperienceLevels() {

        String title = "Current experience levels";
        ArrayList<ArrayList<String>> expLevels = null; //experienceDao.getExperienceLevels();

        display.printList(title, expLevels);

        String coinsEarned = ((Student) user).getExperience();
        Integer missingExp = findNextLevel(coinsEarned, expLevels);
        String rankName = null; //experienceDao.getExperienceInfo(coinsEarned);

        display.printSuccess("You earned total of > " + coinsEarned + " < CoolCoins! Your rank is " + rankName + "!");
        display.printSuccess("You need to earn > " + missingExp + " < more CC for next level\n");
    }

    private Integer findNextLevel(String coins, ArrayList<ArrayList<String>> levels) {

        Integer missingExp = 0;
        Integer current = Integer.parseInt(coins);

        for (int i=1; i < levels.size(); i++) {

            Integer threshold = Integer.parseInt(levels.get(i).get(1));
            missingExp = threshold - current;

            if (threshold > current) {
                break;
            }
        }
        return missingExp;
    }

    private void stopController() {

        controllerRunning = false;
    }
}
