package com.codecool.rmbk.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class ShoppingControllerView extends ConsoleUserView {

    public String handleMainMenu() {
        return handleMenu(createMainMenu());
    }

    public TreeMap<Integer , String> createMainMenu() {

        TreeMap<Integer, String> mainMenu = new TreeMap<>();

        mainMenu.put(1, "Artifacts");
        mainMenu.put(2, "Quests");
        mainMenu.put(3, "Log out");

        return mainMenu;
    }

}
