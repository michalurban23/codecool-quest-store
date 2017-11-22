package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLQuest;
import com.codecool.rmbk.dao.SQLQuestTemplate;
import com.codecool.rmbk.dao.SQLTeam;
import com.codecool.rmbk.model.quest.Quest;
import com.codecool.rmbk.model.quest.QuestTemplate;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ConsoleQuestView;
import com.codecool.rmbk.view.ConsoleView;
import java.util.ArrayList;
import java.util.TreeMap;

class QuestController {

    private ConsoleView display = new ConsoleQuestView();
    private SQLQuest questDAO = new SQLQuest();
    private SQLQuestTemplate templateDAO = new SQLQuestTemplate();
    private SQLTeam teamDAO = new SQLTeam();
    private TreeMap<Integer, String> menu = new TreeMap<>();
    private User user;
    private Team team = null;
    private String accessLevel = "";
    private Boolean controllerRunning;

    void start(User user) {

        this.controllerRunning = true;
        this.user = user;

        if (user.getClass().getSimpleName().equals("Student")) {
            createStudentMenu();
            handleIsGroup();
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

    private void handleIsGroup() {

        Boolean decisionMade = false;
        String choice = display.getInput("Do you want to proceed solo or as a team? (s/t) ");

        while (!decisionMade) {
            switch (choice) {
                case "s":
                    accessLevel = "StudentSolo";
                    decisionMade = true;
                    break;
                case "t":
                    accessLevel = "StudentTeam";
                    selectTeam();
                    decisionMade = true;
                    break;
                default:
                    choice = display.getInput("No such choice. Type 's' or 't': ");
                    break;
            }
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

    private void selectTeam() {

        ArrayList<ArrayList<String>> results = teamDAO.getUserGroups(user);
        display.printList("Your teams", results);

        ArrayList<String> teams = display.getListChoice(results.subList(1, results.size()));
        this.team = (Team) teamDAO.getTeamByName(teams.get(0));
    }

    private void showAllTemplates() {

        String title = "Quest template";
        ArrayList<ArrayList<String>> results = templateDAO.getAllQuestTemplates();

        display.printList(title, results);
    }

    private void addTemplate() {

        display.clearScreen();
        display.printMessage("Creating new template: ");

        String name = display.getInput("What's the name of template? ");
        String description = display.getInput("Enter template description: ");
        Integer value = display.getInteger("How many coins is it worth? ");
        Boolean special = display.getAnswer("Is the quest special? ");

        templateDAO.addQuestTemplate(name, description, value, special);
    }

    private void editTemplate() {

        display.clearScreen();
        display.printMessage("Editing template: ");

        showAllTemplates();
        Integer number = display.getInteger("Which template to edit? ");
        QuestTemplate qt = new QuestTemplate(templateDAO.getAllQuestTemplates().get(number));

        String[] labels = {"Description", "Value", "Special", "Active"};
        String[] newData = changeData(labels);

        qt.updateData(newData);
        // templateDAO.editQuestTemplate(qt.getQuestTemplate());
    }

    private void removeTemplate() {

        display.clearScreen();
        display.printMessage("Removing template: ");

        showAllTemplates();
        Integer number = display.getInteger("Which template to remove? ");
        QuestTemplate qt = new QuestTemplate(templateDAO.getAllQuestTemplates().get(number));

        templateDAO.removeQuestTemplate(qt.getName());
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

    private void showMyQuests() {

        String teamName = team != null ? team.getName() : "solo";
        String title = "My Quests";
        ArrayList<ArrayList<String>> results = questDAO.getMyQuests(user.getID(), teamName);

        display.printList(title, results);
    }

    private void getNewQuest() {

        display.clearScreen();
        display.printMessage("Getting new quest: ");

        showAllTemplates();
        Integer number = display.getInteger("Which quest do you want take? ");
        Quest quest = new Quest(templateDAO.getAllQuestTemplates().get(number), user);

        questDAO.getNewQuest(quest);
    }

    private void submitQuest() {

        display.printWarning("Not Implemented Yet. Soon.");
    }

}
