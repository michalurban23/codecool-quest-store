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
            }else if(choice.equals("Pay cart")) {
                payCart();
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
        Item item = view.getItemsListChoice(shop.getItemsList());

        shop.removeFromCart(item);
    }

    private void listCart() {
        ArrayList<Item> itemsList = shop.getItemsList();
        ArrayList<ArrayList<String>> cartList = view.getDataFromCart(itemsList);
        view.printList("Cart", cartList);
    }

    private void payCart() {
        ;
    }

    private void flushCart() {
        shop.flushCart();
    }

    private void checkWallet() {
        ;
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
        ArrayList<String> choice = view.getListChoice(getAvailableArtifacts());

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

    public String getArtifactInfo(Item artifact) {
        return view.getArtifactQuery(artifact);
    }

}
