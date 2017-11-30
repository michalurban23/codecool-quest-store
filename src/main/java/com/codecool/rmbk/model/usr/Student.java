package com.codecool.rmbk.model.usr;

import java.util.ArrayList;

import com.codecool.rmbk.dao.SQLBacklog;
import com.codecool.rmbk.model.Cart;

public class Student extends User implements Holder {

    private ArrayList<Team> myGroups;
    private Klass myClass;
    private Cart myCart = new Cart();
    private SQLBacklog backlogDao = new SQLBacklog();

    private static ArrayList<Student> objects = new ArrayList<>();

    public Student(String firstName, String lastName, String email, String address, Integer id) {

        super(firstName, lastName, email, address, id);
        objects.add(this);
    }

    public Student(String[] userInfo){

        super(userInfo);
        objects.add(this);
    }

    public Student() {

        this("Not Available", "Not Available", "Not Available", "Not Available", null);
    }

    public Student(int id) {

        this();
        this.id = id;
    }

    public Student(String firstName, String lastName) {

        this(firstName, lastName, "Not Available", "Not Available", null);
    }

    public Cart getCart() {

        return myCart;
    }

    public void setCart(Cart cart) {

        this.myCart = cart;
    }

    public String getExperience() {

        return backlogDao.getExperience(this.id);
    }

}
