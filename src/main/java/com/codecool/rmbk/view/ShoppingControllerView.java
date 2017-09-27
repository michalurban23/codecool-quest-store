package com.codecool.rmbk.view;

import com.codecool.rmbk.model.item.Item;

import java.util.LinkedHashMap;

public class ShoppingControllerView extends ConsoleUserView {


    public Integer handleMainMenu() {
        return handleMenu(createMainMenu());
    }

    public LinkedHashMap<String, Integer> createMainMenu() {

        LinkedHashMap<String, Integer> mainMenu = new LinkedHashMap<>();

        mainMenu.put("View available artifacts", 1);
        mainMenu.put("Buy artifact", 2);
        mainMenu.put("Buy artifact with group", 3);
        mainMenu.put("Log out", 0);

        return mainMenu;
    }

    public Integer handleDetailsMenu(Item item) {

        showShortInfo(item);
        System.out.println("\n");

        return handleMenu(createDetailsMenu());
    }

    private LinkedHashMap<String,Integer> createDetailsMenu() {

        LinkedHashMap<String,Integer> artifactMenu = new LinkedHashMap<>();

        artifactMenu.put("View detailed artifact info", 1);
        artifactMenu.put("Back", 0);

        return artifactMenu;
    }

}
