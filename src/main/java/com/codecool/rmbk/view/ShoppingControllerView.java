package com.codecool.rmbk.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ShoppingControllerView extends ConsoleUserView {


    public Integer handleMainMenu() {
        return handleMenu(createMainMenu());
    }

    public LinkedHashMap<String, Integer> createMainMenu() {

        LinkedHashMap<String, Integer> mainMenu = new LinkedHashMap<>();

        mainMenu.put("View available artifacts", 1);
        mainMenu.put("Buy artifact", 2);
        mainMenu.put("Buy artifact with group", 3);
        mainMenu.put("Log out", 0);

        return mainMenu;
    }

    public void listDetailedArtifacts(ArrayList<ArrayList<String>> artifacts) {
        String toPrint = "";

        for(ArrayList artifact : artifacts) {

//            showEnumeratedList(artifact);

            toPrint += String.valueOf(artifacts.indexOf(artifact) + 1);
            toPrint += ". Artifact name :: " + artifact.get(0) + " ::\n";
            toPrint += "Description :: " + artifact.get(1) + " ::\n";
            toPrint += "Cost :: " + artifact.get(2) + " ::\n";

            if(artifact.get(3).equals(1)) {
                toPrint += ":: SPECIAL QUEST ::";
            }

            System.out.println(toPrint);

            toPrint = "";
        }
    }

    public void listArtifacts(ArrayList<ArrayList<String>> artifacts) {
        String toPrint = "";

        for(ArrayList artifact : artifacts) {
//            showEnumeratedList(artifact);

            toPrint += String.valueOf(artifacts.indexOf(artifact) + 1) + "Artifact name :: " + artifact.get(0) + " ::\n";

            System.out.println(toPrint);

            toPrint = "";

        }
    }

}
