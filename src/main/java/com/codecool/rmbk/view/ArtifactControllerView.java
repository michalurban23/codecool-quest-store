package com.codecool.rmbk.view;

import com.codecool.rmbk.model.item.Item;
import com.codecool.rmbk.model.item.ItemTemplate;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class ArtifactControllerView extends ConsoleUserView {

    public String handleStudentMenu() {


        return handleMenu(createStudentMenu());
    }

    public String handleMentorMenu() {


        return handleMenu(createMentorMenu());
    }

    public LinkedHashMap<Integer, String> createStudentMenu() {

        LinkedHashMap<Integer, String> mainMenu = new LinkedHashMap<>();

        mainMenu.put(1, "View available artifacts");
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

    public void viewDetailedTemplate(ItemTemplate template) {
    }

<<<<<<< HEAD
=======
    public String getArtifactQuery(Item artifact) {

        String template = artifact.getTemplate().getName();
        String owner = artifact.getOwner().toString();
        String completion = artifact.getCompletion();

        return "VALUES ('"+template+"', '"+owner+"', '"+completion+"')";
    }

    public String[] getArtifactQueryArray(Item artifact) {

        String template = artifact.getTemplate().getName();
        String owner = artifact.getOwner().toString();
        String completion = artifact.getCompletion();

        return new String[]{template, owner, completion};
    }


>>>>>>> dev
    public String getArtifactTemplateQuery(ItemTemplate template) {

        String name = template.getName();
        String description = template.getDescription();
        String value = template.getValue();
        String special = template.getSpecial();

        return "VALUES ('"+name+"', '"+description+"', '"+value+"', '"+special+"')";
    }

    public String[] getArtifactTemplateQueryArray(ItemTemplate template) {

        String name = template.getName();
        String description = template.getDescription();
        String value = template.getValue();
        String special = template.getSpecial();

        return new String[] {name, description, value, special};
    }
}
