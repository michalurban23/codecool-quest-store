package com.codecool.rmbk.model.usr;

public class Mentor extends User {

    public Mentor(String firstName, String lastName, String email, String address, Integer id) {

        super(firstName, lastName, email, address, id);
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
