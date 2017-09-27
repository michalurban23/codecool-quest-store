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
            Integer choice = view.handleMainMenu();
            if(choice == 1) {
                ArtifactController artifactController = new ArtifactController(user);
                artifactController.handleArtifactController();
            } else if(choice == 2) {
                QuestController questController = new QuestController();
//                questController.handleQuestController();
            } else if(choice == 0) {
                isBrowsed = false;
            }

        }
    }


}
