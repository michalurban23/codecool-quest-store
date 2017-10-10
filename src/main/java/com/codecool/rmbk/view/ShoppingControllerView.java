package com.codecool.rmbk.view;

import com.codecool.rmbk.model.item.Item;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class ShoppingControllerView extends ConsoleUserView {

    public String handleMainMenu() {
        return handleMenu(createMainMenu());
    }

    public LinkedHashMap<Integer , String> createMainMenu() {

        LinkedHashMap<Integer, String> mainMenu = new LinkedHashMap<>();

        mainMenu.put(1, "Add artifact to cart");
        mainMenu.put(2, "Remove artifact from cart");
        mainMenu.put(3, "List cart");
        mainMenu.put(4, "Flush cart");
        mainMenu.put(5, "Check wallet");
        mainMenu.put(6, "Checkout");
        mainMenu.put(0, "Log out");

        return mainMenu;
    }

    public ArrayList<ArrayList<String>> getDataFromCart(ArrayList<Item> itemsList) {
        ArrayList<ArrayList<String>> toReturn = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();

        title.add("Name");
        title.add("Value (CC)");
        toReturn.add(title);

        for(Item item : itemsList) {
            ArrayList<String> toAdd = new ArrayList<>();
            toAdd.add(item.getTemplate().getName());
            toAdd.add(item.getTemplate().getValue());
            toReturn.add(toAdd);
        }
        return toReturn;
    }

    public Item getItemsListChoice(ArrayList<Item> itemsList) {
        Integer choice = null;

        do {
            choice = getInteger("Choose index: ");
            if (choice == null) {
                return null;
            }
        } while (choice < 1 || choice > itemsList.size());

        return itemsList.get(choice - 1);
    }

    public void printWalletInfo(Integer coins) {
        if(coins <= 0) {
            String message = "You're broke buddy. Your current balance is: " + coins.toString();
            printError(message);
        }
        else {
            String message =  "You're current balance is: " + coins.toString();
            printSuccess(message);
        }
    }
}
