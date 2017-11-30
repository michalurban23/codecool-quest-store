package com.codecool.rmbk.view.console;

import com.codecool.rmbk.model.item.ItemTemplate;

import java.util.LinkedHashMap;

public class ArtifactControllerView extends ConsoleUserView {

    public String handleStudentMenu() {


        return handleMenu(createStudentMenu());
    }

    public String handleMentorMenu() {


        return handleMenu(createMentorMenu());
    }

    public LinkedHashMap<Integer, String> createStudentMenu() {

        LinkedHashMap<Integer, String> mainMenu = new LinkedHashMap<>();

        mainMenu.put(1, "Show my artifacts");
        mainMenu.put(2, "Go to shopping centre");
        mainMenu.put(0, "Log out");

        return mainMenu;
    }

    public LinkedHashMap<Integer, String> createMentorMenu() {

        LinkedHashMap<Integer, String> mainMenu = new LinkedHashMap<>();

        mainMenu.put(1, "View artifact templates");
        mainMenu.put(2, "Create new template");
        mainMenu.put(3, "Edit existing template");
        mainMenu.put(0, "Log out");

        return mainMenu;
    }

    public String[] getArtifactTemplateQueryArray(ItemTemplate template) {

        String name = template.getName();
        String description = template.getDescription();
        String value = template.getValue();
        String special = template.getSpecial();

        return new String[] {name, description, value, special};
    }
}
