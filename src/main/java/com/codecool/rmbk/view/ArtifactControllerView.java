package com.codecool.rmbk.view;

import java.util.LinkedHashMap;

public class ArtifactControllerView extends ConsoleUserView {

    public Integer handleMainMenu() {
        return handleMenu(createMainMenu());
    }

    public LinkedHashMap<String, Integer> createMainMenu() {

        LinkedHashMap<String, Integer> mainMenu = new LinkedHashMap<>();

        mainMenu.put("View available artifacts", 1);
        mainMenu.put("Buy artifact", 2);
        mainMenu.put("Buy artifact as group", 3);
        mainMenu.put("Log out", 0);

        return mainMenu;
    }


}
