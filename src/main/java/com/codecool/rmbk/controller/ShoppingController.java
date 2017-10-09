package com.codecool.rmbk.controller;

import com.codecool.rmbk.model.Shopping;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ShoppingControllerView;

public class ShoppingController {

    private Shopping shop;
    private ShoppingControllerView view;

    public ShoppingController(User user, Shopping shop) {
        this.view = new ShoppingControllerView();
        this.shop = shop;
    }

    public void startShoppingController() {
        handleShoppingMenu();
    }

    public void handleShoppingMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            String choice = view.handleMainMenu();
            if(choice.equals("Buy artifact")) {
                shop.buyItem();
            } else if(choice.equals("Check wallet")) {
                shop.checkWallet();
            } else if(choice.equals("Log out")) {
                isBrowsed = false;
            }
        }
    }
}
