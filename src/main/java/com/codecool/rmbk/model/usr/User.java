package com.codecool.rmbk.model.usr;

import java.util.UUID;
import java.util.ArrayList;

public abstract class User {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String address;

    protected Integer id;
    protected String status;
    protected static ArrayList<? extends User> objects;

    public User(String firstName, String lastName, String email, String address, Integer id) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.id = id;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getFirstName() {

        return this.firstName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getLastName() {

        return this.lastName;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEmail() {

        return this.email;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getAddress() {

        return this.address;
    }

    public int getID() {

        return this.id;
    }

    public String getFullName() {

        return this.firstName + " " + this.lastName;
    }

    public String getStatus() {

        return this.status;
    }

    public String toString() {

        return this.firstName + " " + this.lastName;
    }

}
