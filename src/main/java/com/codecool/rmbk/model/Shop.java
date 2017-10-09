package com.codecool.rmbk.model;

import com.codecool.rmbk.model.item.Item;

import java.util.ArrayList;

public class Shop {

    private Cart cart;
    private Integer id;

    public Shop(Cart cart, Integer id) {
        this.cart = cart;
        this.id = id;
    }

    public void checkWallet() {
        ;
    }

    public void addToCart(Item item) {
        cart.addToCart(item);
    }

    public ArrayList<Item> getItemsList() {
        ArrayList<Item> itemsList = cart.getItemsList();

        return itemsList;
    }

    public void removeFromCart(Item item) {
        cart.removeFromCart(item);
    }

    public void flushCart() {
        cart.flushCart();
    }

    public Integer getId() {
        return id;
    }
}
