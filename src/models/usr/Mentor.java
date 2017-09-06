package models.usr;

import models.usr.Class;

public class Mentor extends User {

    private Class myClass;

    public Mentor(String firstName, String lastName, String email, String address) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.status = ACCESS_LEVEL.MENTOR;
    }

    public Mentor() {

        this("Not Available", "Not Available", "Not Available", "Not Available");
    }

    public Student(String firstName, String lastName) {

        this(firstName, lastName, "Not Available", "Not Available");
    }

    public
}
