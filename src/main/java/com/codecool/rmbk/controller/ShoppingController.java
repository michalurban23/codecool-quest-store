package com.codecool.rmbk.controller;

import com.codecool.rmbk.model.usr.User;

public class ShoppingController {

    private User user;
    private ShoppingControllerView view;

    public ShoppingController(User user) {
        this.user = user;
    }

    public void startShoppingController() {

        handleShoppingMenu();
    }

    public void handleShoppingMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
        }
    }
}
