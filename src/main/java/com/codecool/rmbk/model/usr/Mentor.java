package com.codecool.rmbk.model.usr;

import java.util.ArrayList;

public class Mentor extends User {

    private static ArrayList<Mentor> objects = new ArrayList<Mentor>();

    public Mentor(String firstName, String lastName, String email, String address, Integer id) {

        super(firstName, lastName, email, address, id);
        this.status = "MENTOR";
        objects.add(this);
    }

    public Mentor() {

        this("Not Available", "Not Available", "Not Available", "Not Available", null);
    }

    public Mentor(String firstName, String lastName) {

        this(firstName, lastName, "Not Available", "Not Available", null);
    }

    public static ArrayList<Mentor> getObjects(){
        return objects;
    }

    public static boolean remove(User user) {

        return objects.remove(user);
    }
}
