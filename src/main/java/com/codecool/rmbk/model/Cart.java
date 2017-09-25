package com.codecool.rmbk.model;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Object> itemList;

    public Cart(ArrayList<Object> itemList) {

        this.itemList = itemList;
    }

    private ArrayList<Object> getItemList() {

        return this.itemList;
    }

    private void addToCart(Object item) {

        itemList.add(item);
    }

    private void removeFromCart(Object item) {

        if(itemList.contains(item)) {
            itemList.remove(item);
        }
    }

    private void flushCart() {

        itemList.clear();
    }

}
