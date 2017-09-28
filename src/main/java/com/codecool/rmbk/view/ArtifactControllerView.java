package com.codecool.rmbk.view;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class ArtifactControllerView extends ConsoleUserView {

    public String handleStudentMenu() {


        return handleMenu(createStudentMenu());
    }

    public String handleMentorMenu() {


        return handleMenu(createMentorMenu());
    }

    public TreeMap<Integer, String> createStudentMenu() {

        TreeMap<Integer, String> mainMenu = new TreeMap<>();

        mainMenu.put(1, "View available artifacts");
        mainMenu.put(2, "Buy artifact");
        mainMenu.put(3, "Buy as group");
        mainMenu.put(0, "Log out");

        return mainMenu;
    }

    public TreeMap<Integer, String> createMentorMenu() {

        TreeMap<Integer, String> mainMenu = new TreeMap<>();

        mainMenu.put(1, "View artifact templates");
        mainMenu.put(2, "Create new template");
        mainMenu.put(0, "Log out");

        return mainMenu;
    }


}
