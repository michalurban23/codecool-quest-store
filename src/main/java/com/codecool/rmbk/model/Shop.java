package com.codecool.rmbk.model;

import com.codecool.rmbk.model.item.Item;

import java.util.ArrayList;

public class Shop {

    private Cart cart;
    private Wallet wallet;
    private Integer id;

    public Shop(Cart cart, Integer id) {
        this.cart = cart;
        this.id = id;
        this.wallet = new Wallet(id);
    }

    public Integer checkWallet() {
        return wallet.getCoins();
    }

    public void addToCart(Item item) {
        cart.addToCart(item);
    }

    public ArrayList<Item> getItemsList() {

        return cart.getItemsList();
    }

    public Integer getTotalPrice() {
        Integer totalPrice = 0;
        for(Item item : cart.getItemsList()) {
            Integer price = Integer.parseInt(item.getTemplate().getValue());
            totalPrice += price;
        }
        return totalPrice;
    }

    public void payForCart() {
        for(Item item : cart.getItemsList()) {
            String[] data = new String[5];
            data[0] = item.getBuyTime();
            data[1] = item.getTemplate().getName();
            data[2] = "bought";
            data[3] = item.getTemplate().getValue();
            data[4] = this.id.toString();
            wallet.updateWallet(data);
            }
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
