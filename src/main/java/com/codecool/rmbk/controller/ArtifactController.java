package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.model.Shop;
import com.codecool.rmbk.model.item.ItemTemplate;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.ArtifactControllerView;
import java.util.ArrayList;

class ArtifactController {

    private User user;
    private ArtifactControllerView view;

    ArtifactController() {
        this.view = new ArtifactControllerView();
    }

    void start(User user) {

        this.user = user;

        if(user.getClass().getSimpleName().equals("Student")) {
            handleStudentMenu();
        }
        else {
            handleMentorMenu();
        }
    }

    private void handleStudentMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            String choice = view.handleStudentMenu();

            switch (choice) {
                case "View available artifacts":
                    listArtifacts();
                    break;
                case "Go to shopping centre":
                    goToShoppingController();
                    break;
                case "Log out":
                    isBrowsed = false;
                    break;
            }
        }
    }

    private void handleMentorMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            String choice = view.handleMentorMenu();

            switch (choice) {
                case "View artifact templates":
                    listArtifacts();
                    break;
                case "Create new template":
                    createArtifactTemplate();
                    break;
                case "Edit existing template":
                    editExistingTemplate();
                    break;
                case "Log out":
                    isBrowsed = false;
                    break;
            }
        }
    }

    private void listArtifacts() {

        ArrayList<ArrayList<String>> artifacts = getAvailableArtifacts();
        view.printList("Artifacts", artifacts);
    }


    private void goToShoppingController() {


        Student student = (Student) this.user;
        Shop shop = new Shop(student.getCart(), student.getID());

        ShoppingController shopControl = new ShoppingController(shop, student);
        shopControl.startShoppingController();
    }

    private ItemTemplate getArtifactTemplate() {

        listArtifacts();
        ArrayList<String> choice = view.getListChoice(getAvailableArtifacts());

        ItemTemplate template = new ItemTemplate(choice.get(0), choice.get(1), choice.get(2), choice.get(3));

        return template;
    }

    private ArrayList<ArrayList<String>> getAvailableArtifacts() {

        SQLArtifactTemplate artifactDao = new SQLArtifactTemplate();
        artifactDao.getAllArtifactTemplates();

        ArrayList<ArrayList<String>> artifacts = artifactDao.getResults();

        return artifacts;
    }

    public ArrayList<ArrayList<String>> getMyArtifacts() {
        SQLArtifact artifactDao = new SQLArtifact();
        artifactDao.getArtifact(String.valueOf(user.getID()));
        ArrayList<ArrayList<String>> myArtifacts = artifactDao.getResults();
        return myArtifacts;
    }

    private void createArtifactTemplate() {

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

    private void editExistingTemplate() {

        ItemTemplate toEdit = getArtifactTemplate();

        removeExistingTemplate(toEdit);
        createArtifactTemplate();
    }

    private void removeExistingTemplate(ItemTemplate template) {

        SQLArtifactTemplate artifactTemplates = new SQLArtifactTemplate();
        artifactTemplates.removeArtifactTemplate(template.getName());
    }


    private void addArtifactTemplateToDatabase(ItemTemplate template) {

        SQLArtifactTemplate artifactTemplates = new SQLArtifactTemplate();
        artifactTemplates.addArtifactTemplate(getArtifactTemplateInfoArray(template));
    }

    private String[] getArtifactTemplateInfoArray(ItemTemplate template) {

        return view.getArtifactTemplateQueryArray(template);
    }

}
