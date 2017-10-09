package com.codecool.rmbk.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class ShoppingControllerView extends ConsoleUserView {

    public String handleMainMenu() {
        return handleMenu(createMainMenu());
    }

    public LinkedHashMap<Integer , String> createMainMenu() {

        LinkedHashMap<Integer, String> mainMenu = new LinkedHashMap<>();

        mainMenu.put(1, "Buy artifact");
        mainMenu.put(2, "Check wallet");
        mainMenu.put(3, "Log out");

        return mainMenu;
    }

}
