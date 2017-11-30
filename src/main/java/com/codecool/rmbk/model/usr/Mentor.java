package com.codecool.rmbk.model.usr;

import java.util.ArrayList;

public class Mentor extends User {

    private static ArrayList<Mentor> objects = new ArrayList<>();

    public Mentor(String firstName, String lastName, String email, String address, Integer id) {

        super(firstName, lastName, email, address, id);
        objects.add(this);
    }

    public Mentor(String[] userInfo){

        super(userInfo);
    }

    public Mentor() {

        this("Not Available", "Not Available", "Not Available", "Not Available", null);
    }

    public Mentor(int id) {

        this();
        this.id = id;
    }

    public Mentor(String firstName, String lastName) {

        this(firstName, lastName, "Not Available", "Not Available", null);
    }
}
