package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.model.item.Item;
import com.codecool.rmbk.model.item.ItemTemplate;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ArtifactControllerView;

import java.util.ArrayList;

public class ArtifactController {

    private User user;
    private ArtifactControllerView view;

    public ArtifactController() {
        this.view = new ArtifactControllerView();
    }

    public void start(User user) {

        if(user.getStatus().equals("Student")) {
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
        ItemTemplate template = getNewArtifactTemplate();
        Item artifact = getNewArtifact(template);

        System.out.println(artifact.getTemplate().getDescription());
    }

    public void buyAsGroup() {

    }

    public ItemTemplate getNewArtifactTemplate() {
        listArtifacts();
        ArrayList<String> choice = view.getListChoice(getAvailableArtifacts());

        ItemTemplate template = new ItemTemplate(choice.get(0), choice.get(1),
                                                 Integer.parseInt(choice.get(2)),
                                                 Integer.parseInt(choice.get(3)));

        return template;
    }

    public Item getNewArtifact(ItemTemplate template) {
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

    }

}
