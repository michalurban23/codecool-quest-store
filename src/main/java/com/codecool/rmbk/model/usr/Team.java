package com.codecool.rmbk.model.usr;

import com.codecool.rmbk.model.Cart;
import com.codecool.rmbk.model.quest.Quest;

import java.util.ArrayList;

public class Team extends Group implements Holder {
    private Integer groupID;
    private ArrayList<User> usersList;
    private Cart ourCart;
    private String name;

    public Team(ArrayList<User> usersList) {

        this.usersList = usersList;
    }

    public Team() {

        this(new ArrayList<User>());
    }

    public Team(Integer id, String name) {

        this.groupID = id;
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getID() {
        return this.groupID;
    }

    public ArrayList<User> getUsersList() {
        return this.usersList;
    }

    public Cart getCart() {
        return this.ourCart;
    }

    public void setCart(Cart cart) {
        this.ourCart = cart;
    }

    public Quest getQuest() {return null;} // ---------------------IMPLEMENT---------------------------------

    public void createQuest() {;}

}
