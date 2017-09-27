package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ShoppingControllerView;

import java.util.ArrayList;

public class ShoppingController {

    private User user;
    private ShoppingControllerView view;

    public ShoppingController(User user) {
        this.view = new ShoppingControllerView();
        this.user = user;
    }

    public void startShoppingController() {

        handleShoppingMenu();
    }

    public void handleShoppingMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            Integer choice = view.handleMainMenu();
            if(choice == 1) {
                listDetailedArtifacts();
            } else if(choice == 2) {
                buyArtifact();
            } else if(choice == 3) {
                buyAsGroup();
            } else if(choice == 0) {
                isBrowsed = false;
            }

        }
    }

    public void listDetailedArtifacts() {
        ArrayList<ArrayList<String>> artifacts = getAvailableArtifacts();
        view.listDetailedArtifacts(artifacts);
    }

    public void buyArtifact() {
        ArrayList<ArrayList<String>> artifacts = getAvailableArtifacts();
        view.listArtifacts(artifacts);

        view.getListChoice(artifacts);
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
