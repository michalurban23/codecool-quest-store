package com.codecool.rmbk.view;

import java.util.LinkedHashMap;

public class ArtifactControllerView extends ConsoleUserView {

    public Integer handleStudentMenu() {


        return handleMenu(createStudentMenu());
    }

    public Integer handleMentorMenu() {


        return handleMenu(createMentorMenu());
    }

    public LinkedHashMap<String, Integer> createStudentMenu() {

        LinkedHashMap<String, Integer> mainMenu = new LinkedHashMap<>();

        mainMenu.put("View available artifacts", 1);
        mainMenu.put("Buy artifact", 2);
        mainMenu.put("Buy artifact as group", 3);
        mainMenu.put("Log out", 0);

        return mainMenu;
    }

    public LinkedHashMap<String, Integer> createMentorMenu() {

        LinkedHashMap<String, Integer> mainMenu = new LinkedHashMap<>();

        mainMenu.put("View artifact templates", 1);
        mainMenu.put("Create new template", 2);
        mainMenu.put("Log out", 0);

        return mainMenu;
    }


}
