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
                listArtifacts();
            } else if(choice == 2) {
                buyArtifact();
            } else if(choice == 3) {
                buyAsGroup();
            } else if(choice == 0) {
                isBrowsed = false;
            }

        }
    }

    public void listArtifacts() {
    }

    public void buyArtifact() {

    }

    public void buyAsGroup() {

    }
}
