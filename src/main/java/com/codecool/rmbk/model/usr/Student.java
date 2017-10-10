package com.codecool.rmbk.model.usr;

import java.util.ArrayList;

import com.codecool.rmbk.dao.SQLBacklog;
import com.codecool.rmbk.model.Cart;

public class Student extends User implements Holder {

    private ArrayList<Team> myGroups;
    private Klass myClass;
    private Cart myCart;
    private SQLBacklog backlogDao = new SQLBacklog();
    private static ArrayList<Student> objects = new ArrayList<Student>();

    public Student(String firstName, String lastName, String email, String address, Integer id) {

        super(firstName, lastName, email, address, id);
        this.myCart = new Cart();
        objects.add(this);
    }

    public Student(String[] userInfo){

        super(userInfo);
        this.myCart = new Cart();
        objects.add(this);
    }

    public Student() {

        this("Not Available", "Not Available", "Not Available", "Not Available", null);
        this.myCart = new Cart();
    }

    public Student(int id) {

        this();
        this.id = id;
    }

    public Student(String firstName, String lastName) {

        this(firstName, lastName, "Not Available", "Not Available", null);
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

    public String getExperience() {

        return backlogDao.getExperience(this.id);
    }

}
