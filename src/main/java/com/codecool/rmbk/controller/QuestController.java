package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ConsoleQuestView;
import com.codecool.rmbk.view.ConsoleView;

import java.util.ArrayList;
import java.util.TreeMap;

public class QuestController {

    private ConsoleView display = new ConsoleQuestView();
    private SQLArtifactTemplate dao = new SQLArtifactTemplate();
    private Boolean controllerRunning;

    public void start(User user) {

        this.controllerRunning = true;

        if (user.getStatus().equals("student")) {
            runStudentMenu();
        } else {
            runMentorMenu();
        }
    }

    private void runStudentMenu() {

        ;
    }

    private void runMentorMenu() {

        while (controllerRunning) {
            display.showMenu("Quest menu", getMentorMenu());
            handleMenuChoice();
        }
    }

    void handleMenuChoice() {

        String userChoice = display.getInput("Select an option: ");

        try {
            Integer choice = Integer.parseInt(userChoice);
            handleMentorMenu(choice);
        } catch (NumberFormatException e) {
            display.printError("Invalid input! Try again.");
            display.pause();
        }
    }

    private TreeMap<Integer, String> getStudentMenu() {

        return null;
    }

    private TreeMap<Integer, String> getMentorMenu() {

        TreeMap<Integer, String> menu = new TreeMap<>();

        menu.put(1, "Show all available quest templates");
        menu.put(2, "Add new template");
        menu.put(3, "Edit a template");
        menu.put(4, "Remove a template");

        return menu;
    }

    private void handleStudentMenu(Integer choice) {

        ;
    }

    private void handleMentorMenu(Integer choice) {

        switch (choice) {

            case 1:
                showAllTemplates();
                break;
            case 2:
                addTemplate();
                break;
            case 3:
                editTemplate();
                break;
            case 4:
                removeTemplate();
                break;
            case 0:
                stopController();
                break;
            default:
                display.printWarning("No such option available");
                display.pause();
        }
    }

    private void showAllTemplates() {

        String title = "Quest template";
        ArrayList<ArrayList<String>> results = dao.getAllArtifactTemplates();

        display.printList(title, results);
    }

    private void addTemplate() {

        display.clearScreen();
        display.printMessage("Creating new template: ");

        String name = display.getInput("What's the name of template? ");
        String description = display.getInput("Enter template description: ");
        Integer value = display.getInteger("How many coins is it worth? ");
        Boolean special = display.getAnswer("Is the quest special? ");

        dao.addArtifactTemplate(name, description, value, special);
    }

    private void editTemplate() {;}

    private void removeTemplate() {;}

    private void stopController() {

        controllerRunning = false;
    }

}
