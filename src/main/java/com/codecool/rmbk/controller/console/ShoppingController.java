package com.codecool.rmbk.controller.console;

import java.util.ArrayList;
import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.model.Shop;
import com.codecool.rmbk.model.item.Item;
import com.codecool.rmbk.model.item.ItemTemplate;
import com.codecool.rmbk.view.console.ShoppingControllerView;

class ShoppingController {

    private Shop shop;
    private ShoppingControllerView view;
    private boolean isBrowsed;

    ShoppingController(Shop shop) {

        this.view = new ShoppingControllerView();
        this.shop = shop;
    }

    void startShoppingController() {

        isBrowsed = true;

        while (isBrowsed) {
            String choice = view.handleMainMenu();
            handleShoppingMenu(choice);
        }
    }

    private void handleShoppingMenu(String choice) {

        switch (choice) {
            case "Add artifact to cart":
                addToCart();
                break;
            case "Remove artifact from cart":
                removeFromCart();
                break;
            case "Flush cart":
                flushCart();
                break;
            case "List cart":
                listCart();
                break;
            case "Checkout":
                checkout();
                break;
            case "Check wallet":
                checkWallet();
                break;
            case "Log out":
                isBrowsed = false;
                break;
        }
    }

    private void addToCart() {

        Item artifact = getArtifact();
        if(artifact.getTemplate().getSpecial().equals("1")) {
            view.printError("Sorry, group buying not available yet!");
        }
        else {
            shop.addToCart(artifact);
        }
    }

    private void removeFromCart() {

        listCart();
        Boolean cartIsEmpty = shop.getItemsList().size() == 0;

        if (!cartIsEmpty) {
            Item item = view.getListChoice(shop.getItemsList());
            shop.removeFromCart(item);
        }
    }

    private void listCart() {

        ArrayList<Item> itemsList = shop.getItemsList();
        ArrayList<ArrayList<String>> cartList = view.getDataFromCart(itemsList);

        view.printList("Cart", cartList);
    }

    private void checkout() {

        Boolean notEnoughCoins = shop.checkWallet() <= shop.getTotalPrice();

        if (notEnoughCoins) {
            view.printError("Sorry. You don't have enough coins. Try removing some items from the cart.");
        } else {
            listCart();
            view.printWarning("Total price > " + shop.getTotalPrice().toString() + " <");

            if (view.getInput("Are you sure you want to pay for those items?\n").equals("y")) {
                view.printSuccess("Congratulations! You've successfully paid for your items.");

                addArtifactsAfterPayment(shop.getItemsList());
                shop.payForCart();
                flushCart();

                view.printSuccess("Your current balance is " + shop.checkWallet().toString() + ".");
            }
        }
    }

    private void flushCart() {

        Boolean cartIsEmpty = shop.getItemsList().size() == 0;

        if (cartIsEmpty) {
            view.printWarning("No matching data in Cart");
        } else {
            shop.flushCart();
        }
    }

    private void checkWallet() {

        view.printWalletInfo(shop.checkWallet());
    }

    private Item getArtifact() {

        ItemTemplate template = getArtifactTemplate();
        Item artifact = getNewArtifact(template);

        return artifact;
    }

    private ArrayList<ArrayList<String>> getAvailableArtifacts() {

        SQLArtifactTemplate artifactDao = new SQLArtifactTemplate();
        artifactDao.getAllArtifactTemplates();

        return artifactDao.getResults();
    }

    private ItemTemplate getArtifactTemplate() {

        listArtifacts();
        ArrayList<ArrayList<String>> queryResults = getAvailableArtifacts();
        ArrayList<String> choice = view.getListChoice(queryResults.subList(1, queryResults.size()));

        ItemTemplate template = new ItemTemplate(choice.get(0), choice.get(1), choice.get(2), choice.get(3));

        return template;
    }

    private void listArtifacts() {

        ArrayList<ArrayList<String>> artifacts = getAvailableArtifacts();
        view.printList("Artifacts", artifacts);
    }

     private void addArtifactToDatabase(Item artifact) {

         SQLArtifact artifacts = new SQLArtifact();
//         artifacts.addArtifact(getArtifactInfo(artifact));
     }

     private void addArtifactsAfterPayment(ArrayList<Item> itemsList) {

         for(Item item : itemsList) {
             addArtifactToDatabase(item);
         }
     }


    private Item getNewArtifact(ItemTemplate template) {

        return new Item(template, shop.getId());
    }

    private String[] getArtifactInfo(Item artifact) {

       String[] info  = new String[3];

       info[0] = artifact.getTemplate().getName();
       info[1] = artifact.getOwner().toString();
       info[2] = artifact.getCompletion();

       return info;
    }

}
