package com.codecool.rmbk.model.usr;

public class Admin extends User {

    public Admin(String firstName, String lastName, String email, String address, Integer id) {

        super(firstName, lastName, email, address, id);
        this.status = "ADMIN";
    }

    public Admin(String firstName, String lastName) {

        this(firstName, lastName, "Not Available", "Not Available", null);
    }
}
