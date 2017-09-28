package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.model.item.ItemTemplate;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ArtifactControllerView;

import java.util.ArrayList;

public class ArtifactController {

    private User user;
    private ArtifactControllerView view;

    public ArtifactController(User user) {
        this.user = user;
        this.view = new ArtifactControllerView();
    }

    public void startArtifactController() {
        handleArtifactController();
    }

    public void handleArtifactController() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            Integer choice = view.handleMainMenu();
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

    public void listArtifacts() {
        ArrayList<ArrayList<String>> artifacts = getAvailableArtifacts();
        view.printList("Artifacts", artifacts);
    }

    public void buyArtifact() {
        listArtifacts();
        ArrayList<String> choice = view.getListChoice(getAvailableArtifacts());



        ItemTemplate template = new ItemTemplate();

    }

    public void buyAsGroup() {

    }

    public ArrayList<ArrayList<String>> getAvailableArtifacts() {
        SQLArtifactTemplate artifactDao = new SQLArtifactTemplate();
        artifactDao.getAllArtifactTemplates();
        ArrayList<ArrayList<String>> artifacts = artifactDao.getResults();

        return artifacts;
    }

}
