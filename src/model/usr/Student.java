package models.usr;

import java.util.UUID;
import java.util.ArrayList;
import models.Cart;
import models.quest.Quest;

public class Student extends User implements Holder {

    private ArrayList<Group> myGroups;
    private Class myClass;
    private Cart myCart;

    public Student(String firstName, String lastName, String email, String address) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
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

    public Cart getCart() {

        return myCart;
    }

    public void setCart(Cart cart) {

        this.myCart = cart;
    }

    public Quest getQuest() {return new Quest();}
    public void createQuest() {;}

}
