package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.model.item.Item;
import com.codecool.rmbk.model.item.ItemTemplate;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ArtifactControllerView;
import sun.awt.windows.ThemeReader;

import java.util.ArrayList;

public class ArtifactController {

    private User user;
    private ArtifactControllerView view;

    public ArtifactController() {
        this.view = new ArtifactControllerView();
    }

    public void start(User user) {

        this.user = user;

        if(user.getClass().getSimpleName().equals("Student")) {
            handleStudentMenu();
        }
        else {
            handleMentorMenu();
        }
    }

    public void handleStudentMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            String choice = view.handleStudentMenu();
            if(choice.equals("View available artifacts")) {
                listArtifacts();
            } else if(choice.equals("Buy artifact")) {
                buyArtifact();
            } else if(choice.equals("Buy as group")) {
                buyAsGroup();
            } else if(choice.equals("Log out")) {
                isBrowsed = false;
            }

        }
    }

    public void handleMentorMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            String choice = view.handleMentorMenu();
            if(choice.equals("View artifact templates")) {
                listArtifacts();
            } else if(choice.equals("Create new template")) {
                createArtifactTemplate();
            } else if(choice.equals("Edit existing template")) {
                editExistingTemplate();
            } else if(choice.equals("Log out")) {
                isBrowsed = false;
            }

        }

    }

    public void listArtifacts() {
        ArrayList<ArrayList<String>> artifacts = getAvailableArtifacts();
        view.printList("Artifacts", artifacts);
    }

    public void buyArtifact() {
        ItemTemplate template = getArtifactTemplate();
        Item artifact = getArtifact(template);

        addArtifactToDatabase(artifact);
    }

    public void buyAsGroup() {

    }

    public ItemTemplate getArtifactTemplate() {
        listArtifacts();
        ArrayList<String> choice = view.getListChoice(getAvailableArtifacts());

        ItemTemplate template = new ItemTemplate(choice.get(0), choice.get(1), choice.get(2), choice.get(3));

        return template;
    }

    public Item getArtifact(ItemTemplate template) {
        Item artifact = new Item(template, user.getID());

        return artifact;
    }


    public ArrayList<ArrayList<String>> getAvailableArtifacts() {
        SQLArtifactTemplate artifactDao = new SQLArtifactTemplate();
        artifactDao.getAllArtifactTemplates();
        ArrayList<ArrayList<String>> artifacts = artifactDao.getResults();

        return artifacts;
    }


    public void createArtifactTemplate() {
        String name = view.getInput("Type the name of the artifact: ");
        String value = view.getInput("Set the value: ");
        String description = view.getInput("Write a description: ");
        String special = view.getInput("Is it a special quest: (y/n) ");
        if(special.equals("y")) {
            special = "1";
        } else {
            special = "0";
        }

        ItemTemplate newTemplate = new ItemTemplate(name, description, value, special);
        addArtifactTemplateToDatabase(newTemplate);
    }

    public void editExistingTemplate() {
        ItemTemplate toEdit = getArtifactTemplate();
        removeExistingTemplate(toEdit);
        createArtifactTemplate();
    }

    public void removeExistingTemplate(ItemTemplate template) {
        SQLArtifactTemplate artifactTemplates = new SQLArtifactTemplate();
        artifactTemplates.removeArtifactTemplate(template.getName());
    }

    public void addArtifactToDatabase(Item artifact) {
        SQLArtifact artifacts = new SQLArtifact();
        artifacts.addArtifact(getArtifactInfo(artifact));
    }

    public void addArtifactTemplateToDatabase(ItemTemplate template) {
        SQLArtifactTemplate artifactTemplates = new SQLArtifactTemplate();
        artifactTemplates.addArtifactTemplate(getArtifactTemplateInfo(template));
    }

    public String getArtifactInfo(Item artifact) {
        return view.getArtifactQuery(artifact);
    }

    public String getArtifactTemplateInfo(ItemTemplate template) {
        return view.getArtifactTemplateQuery(template);
    }

}
