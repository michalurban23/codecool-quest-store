package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLQuestTemplate;
import com.codecool.rmbk.model.quest.QuestTemplate;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ConsoleQuestView;
import com.codecool.rmbk.view.ConsoleView;

import java.util.ArrayList;
import java.util.TreeMap;

public class QuestController {

    private ConsoleView display = new ConsoleQuestView();
    private SQLQuestTemplate dao = new SQLQuestTemplate();
    private TreeMap<Integer, String> menu = new TreeMap<>();
    private Boolean controllerRunning;
    private String accessLevel = "";

    void start(User user) {

        this.controllerRunning = true;

        if (user.getClass().getSimpleName().equals("Student")) {
            createStudentMenu();
            accessLevel = "Student";
        } else {
            createMentorMenu();
            accessLevel = "Mentor";
        }

        runMenu();
    }

    private void runMenu() {

        while (controllerRunning) {
            display.showMenu("Quest menu", menu);
            handleMenuChoice();
        }
    }

    private void handleMenuChoice() {

        String userChoice = display.getInput("Select an option: ");

        try {
            Integer choice = Integer.parseInt(userChoice);
            if (accessLevel.equals("Mentor")) {
                handleMentorMenu(choice);
            } else {
                handleStudentMenu(choice);
            }
        } catch (NumberFormatException e) {
            display.printError("Invalid input! Try again.");
            display.pause();
        }
    }

    private void createStudentMenu() {

        menu.put(1, "Show my quests");
        menu.put(2, "Get new quest");
        menu.put(3, "Submit a quest");
    }

    private void createMentorMenu() {

        menu.put(1, "Show all available quest templates");
        menu.put(2, "Add new template");
        menu.put(3, "Edit a template");
        menu.put(4, "Remove a template");
    }

    private void handleStudentMenu(Integer choice) {

        switch (choice) {
            case 1:
                showMyQuests();
                break;
            case 2:
                getNewQuest();
                break;
            case 3:
                submitQuest();
                break;
            case 0:
                stopController();
                break;
            default:
                display.printWarning("No such option available");
                display.pause();
        }
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
        ArrayList<ArrayList<String>> results = dao.getAllQuestTemplates();

        display.printList(title, results);
    }

    private void addTemplate() {

        display.clearScreen();
        display.printMessage("Creating new template: ");

        String name = display.getInput("What's the name of template? ");
        String description = display.getInput("Enter template description: ");
        Integer value = display.getInteger("How many coins is it worth? ");
        Boolean special = display.getAnswer("Is the quest special? ");

        dao.addQuestTemplate(name, description, value, special);
    }

    private void editTemplate() {

        display.clearScreen();
        display.printMessage("Editing template: ");

        showAllTemplates();
        Integer number = display.getInteger("Which template to edit? ");
        QuestTemplate qt = new QuestTemplate(dao.getAllQuestTemplates().get(number));

        String[] labels = {"Description", "Value", "Special", "Active"};
        String[] newData = changeData(labels);

        qt.updateData(newData);
        dao.editQuestTemplate(qt.getQuestTemplate());
    }

    private void removeTemplate() {

        display.clearScreen();
        display.printMessage("Removing template: ");

        showAllTemplates();
        Integer number = display.getInteger("Which template to remove? ");
        QuestTemplate qt = new QuestTemplate(dao.getAllQuestTemplates().get(number));

        dao.removeQuestTemplate(qt.getName());
    }

    private void stopController() {

        controllerRunning = false;
    }

    private String[] changeData(String[] data) {

        String[] newData = new String[4];

        for (int i=0; i < data.length; i++) {
            String input = display.getInput("Enter new value for >" + data[i] +
                                            "<\n(or leave empty for no change) : ");
            newData[i] = input;
        }
        return newData;
    }

    private void showMyQuests() {;}

    private void getNewQuest() {;}

    private void submitQuest() {;}

}
