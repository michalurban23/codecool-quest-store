package com.codecool.rmbk.model.usr;

import java.util.UUID;

public class Admin extends User {

    public Admin() {;}

    public Admin(String firstName, String lastName, String email, String address, String id) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.id = UUID.fromString(id);
        this.status = "Admin";
    }

    public Admin(String firstName, String lastName) {

        this(firstName, lastName, "Not Available", "Not Available", UUID.randomUUID().toString());
    }
}
