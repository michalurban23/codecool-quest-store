package com.codecool.rmbk.model.usr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class User implements Holder {

    static Class<? extends User> supervisor;

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String address;
    protected Integer id;

    public User(String firstName, String lastName, String email, String address, Integer id) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.id = id;
    }

    public User(String[] fields){

        this(fields[1], fields[2], fields[3], fields[4], Integer.parseInt(fields[0]));
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

    public Map<String, String> getFullInfoMap() {
        Map<String, String> fullInfo = new LinkedHashMap<>();
        fullInfo.put("name", getFirstName());
        fullInfo.put("surname", getLastName());
        fullInfo.put("email", getEmail());
        fullInfo.put("address", getAddress());
        return fullInfo;
    }

    public String getFullName() {

        return this.firstName + " " + this.lastName;
    }

    public String getAccessLevel() {

        return this.getClass().getSimpleName();
    }

    public String toString() {

        return getFullName();
    }

    public static List<String> getFieldLabels() {
        List<String> labels = new ArrayList<>();
        labels.add("name");
        labels.add("surname");
        labels.add("email");
        labels.add("address");
        return labels;
    }

    public String getURI() {
        return String.format("/%s/%s", this.getClass().getSimpleName(), this.getID());
    }

    public static Class getSupervisor() {
        return supervisor;
    }

}
