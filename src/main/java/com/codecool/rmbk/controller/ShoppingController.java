package com.codecool.rmbk.controller;

import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ShoppingControllerView;


public class ShoppingController {

    private User user;
    private ShoppingControllerView view;

    public ShoppingController(User user) {
        this.view = new ShoppingControllerView();
        this.user = user;
    }

    public void startShoppingController() {

        handleShoppingMenu();
    }

    public void handleShoppingMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            String choice = view.handleMainMenu();
            if(choice == "Artifacts") {
                ArtifactController artifactController = new ArtifactController();
                artifactController.start(user);
            } else if(choice == "Quests") {
                QuestController questController = new QuestController();
//                questController.handleQuestController();
            } else if(choice == "Log out") {
                isBrowsed = false;
            }

        }
    }


}
