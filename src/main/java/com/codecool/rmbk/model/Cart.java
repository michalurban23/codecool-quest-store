package com.codecool.rmbk.model;

import com.codecool.rmbk.model.item.Item;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Item> itemsList = new ArrayList<>();

    public Cart() {}

    public ArrayList<Item> getItemsList() {
        return this.itemsList;
    }

    public void addToCart(Item item) {
        this.itemsList.add(item);
    }

    public void removeFromCart(Item item) {

        if(itemsList.contains(item)) {
            itemsList.remove(item);
        }
    }

    public void flushCart() {

        itemsList.clear();
    }

}
