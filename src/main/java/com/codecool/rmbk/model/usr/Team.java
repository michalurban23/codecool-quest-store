package com.codecool.rmbk.model.usr;

import com.codecool.rmbk.model.Cart;

import java.util.ArrayList;

public class Team extends Group implements Holder {

    private Cart ourCart;

    public Team(Integer id, String name) {
        super(id, name);
    }

    public Cart getCart() {
        return this.ourCart;
    }

    public void setCart(Cart cart) {
        this.ourCart = cart;
    }

}
