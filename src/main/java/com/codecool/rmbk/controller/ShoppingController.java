package com.codecool.rmbk.controller;

import com.codecool.rmbk.dao.SQLArtifact;
import com.codecool.rmbk.dao.SQLArtifactTemplate;
import com.codecool.rmbk.model.Shop;
import com.codecool.rmbk.model.item.Item;
import com.codecool.rmbk.model.item.ItemTemplate;
import com.codecool.rmbk.view.ShoppingControllerView;

import java.util.ArrayList;

public class ShoppingController {

    private Shop shop;
    private ShoppingControllerView view;

    public ShoppingController(Shop shop) {
        this.view = new ShoppingControllerView();
        this.shop = shop;

    }

    public void startShoppingController() {
        handleShoppingMenu();
    }

    public void handleShoppingMenu() {

        boolean isBrowsed = true;

        while(isBrowsed) {
            String choice = view.handleMainMenu();
            if(choice.equals("Add artifact to cart")) {
                addToCart();
            } else if(choice.equals("Remove artifact from cart")) {
                removeFromCart();
            }else if(choice.equals("Flush cart")) {
                flushCart();
            } else if(choice.equals("List cart")) {
                listCart();
            }else if(choice.equals("Checkout")) {
                checkout();
            }else if(choice.equals("Check wallet")) {
                checkWallet();
            }else if(choice.equals("Log out")) {
                isBrowsed = false;
            }
        }
    }

    private void addToCart() {
        Item artifact = getArtifact();

        shop.addToCart(artifact);
    }

    private void removeFromCart() {
        listCart();
        if(shop.getItemsList().size() != 0) {
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
        if(shop.checkWallet() <= shop.getTotalPrice()) {
            view.printError("Sorry. You don't have enough coins. Try removing some items from the cart.");
        }
        else {
            listCart();
            view.printError("Total price > " + shop.getTotalPrice().toString() + " <");
            if(view.getInput("Are you sure you want to pay for those items?\n").equals("y")) {
                view.printSuccess("Congratulations! You've succesfully paid for your items.");

                addArtifactsAfterPayment(shop.getItemsList());
                shop.payForCart();
                flushCart();

                view.printSuccess("You're current balance is " + shop.checkWallet().toString() + ".");
            }
        }
    }

    private void flushCart() {
        if(shop.getItemsList().size() != 0) {
            shop.flushCart();
        }
        else {
            view.printWarning("No matching data in Cart");
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

    public ArrayList<ArrayList<String>> getAvailableArtifacts() {
        SQLArtifactTemplate artifactDao = new SQLArtifactTemplate();
        artifactDao.getAllArtifactTemplates();
        ArrayList<ArrayList<String>> artifacts = artifactDao.getResults();

        return artifacts;
    }

    public ItemTemplate getArtifactTemplate() {
        listArtifacts();
        ArrayList<ArrayList<String>> queryResults = getAvailableArtifacts();
        ArrayList<String> choice = view.getListChoice(queryResults.subList(1, queryResults.size()));

        ItemTemplate template = new ItemTemplate(choice.get(0), choice.get(1), choice.get(2), choice.get(3));

        return template;
    }

    public void listArtifacts() {
        ArrayList<ArrayList<String>> artifacts = getAvailableArtifacts();
        view.printList("Artifacts", artifacts);
    }

     public void addArtifactToDatabase(Item artifact) {

         SQLArtifact artifacts = new SQLArtifact();
         artifacts.addArtifact(getArtifactInfo(artifact));
     }

     public void addArtifactsAfterPayment(ArrayList<Item> itemsList) {
         for(Item item : itemsList) {
             addArtifactToDatabase(item);
         }
     }


    public Item getNewArtifact(ItemTemplate template) {
        Item artifact = new Item(template, shop.getId());

        return artifact;
    }

    public String[] getArtifactInfo(Item artifact) {
       String[] info  = new String[3];
       info[0] = artifact.getTemplate().getName();
       info[1] = artifact.getOwner().toString();
       info[2] = artifact.getCompletion();

       return info;
    }

}
