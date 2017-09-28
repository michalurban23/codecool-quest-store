package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.model.item.Item;
import com.codecool.rmbk.model.item.ItemTemplate;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ArtifactControllerView;

import java.util.ArrayList;
import java.util.TreeMap;

public class ArtifactController {

    private User user;
    private ArtifactControllerView view;

    public ArtifactController(User user) {
        this.user = user;
        this.view = new ArtifactControllerView();
    }

    public void startArtifactController() {
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
            Integer choice = view.handleStudentMenu();
            if(choice == 1) {
                listArtifacts();
            } else if(choice == 2) {
                buyArtifact();
            } else if(choice == 3) {
                buyAsGroup();
            } else if(choice == 0) {
                isBrowsed = false;
            }

        }
    }

    public void handleMentorMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            Integer choice = view.handleMentorMenu();
            if(choice == 1) {
                listArtifacts();
            } else if(choice == 2) {
                createArtifactTemplate();
            } else if(choice == 0) {
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

    }

    public void buyAsGroup() {

    }

    public void getNewArtifactTemplate() {
        listArtifacts();
        ArrayList<String> choice = view.getListChoice(getAvailableArtifacts());

        ItemTemplate template = new ItemTemplate(choice.get(0), choice.get(1),
                                                 Integer.parseInt(choice.get(2)),
                                                 Integer.parseInt(choice.get(3)));

        System.out.println(template);
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
