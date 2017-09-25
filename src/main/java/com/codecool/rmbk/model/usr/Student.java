package com.codecool.rmbk.model.usr;

import java.util.UUID;
import java.util.ArrayList;
import com.codecool.rmbk.model.Cart;
import com.codecool.rmbk.model.quest.Quest;

public class Student extends User implements Holder {

    private ArrayList<Group> myGroups;
    private Class myClass;
    private Cart myCart;
    private static ArrayList<Student> objects = new ArrayList<Student>();

    public Student(String firstName, String lastName, String email, String address) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.id = UUID.randomUUID();
        this.status = ACCESS_LEVEL.STUDENT;
        objects.add(this);
    }

    public Student(String[] userInfo){
        firstName = userInfo[0];
        lastName = userInfo[1];
        email = userInfo[2];
        address = userInfo[3];
        objects.add(this);
        this.id = UUID.randomUUID();
        this.status = ACCESS_LEVEL.STUDENT;
    }

    public Student() {

        this("Not Available", "Not Available", "Not Available", "Not Available");
    }

    public Student(String firstName, String lastName) {

        this(firstName, lastName, "Not Available", "Not Available");
    }

    public Student(String firstName, String lastName, String email, String address, String id) {

        this(firstName, lastName, email, address);
        this.id = UUID.fromString(id);
    }

    public static ArrayList<Student> getObjects(){

        return objects;
    }

    public Cart getCart() {

        return myCart;
    }

    public static boolean remove(User user) {

        return objects.remove(user);
    }

    public void setCart(Cart cart) {

        this.myCart = cart;
    }

    public Quest getQuest() {return null;} // ---------------------IMPLEMENT---------------------------------
    public void createQuest() {;}

}
