package com.codecool.rmbk.model.usr;

import java.util.UUID;
import java.util.ArrayList;
import com.codecool.rmbk.model.Cart;
import com.codecool.rmbk.model.quest.Quest;

public class Group implements Holder {
    private UUID groupID;
    private ArrayList<User> usersList;
    private Cart ourCart;
    private String name;
    private static ArrayList<Group> objects = new ArrayList<>();

    public Group(ArrayList<User> usersList) {

        this.groupID = UUID.randomUUID();
        this.usersList = usersList;
        objects.add(this);
    }

    public Group() {

        this(new ArrayList<User>());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getID() {
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

    public static ArrayList<Group> getObjects(){

        return objects;
    }

    public static boolean remove(Group group) {

        return objects.remove(group);
    }

    public Quest getQuest() {return null;} // ---------------------IMPLEMENT---------------------------------

    public void createQuest() {;}

}
