package model.usr;

import java.util.UUID;

public class Mentor extends User {

    private Class myClass;

    public Mentor(String firstName, String lastName, String email, String address) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.id = UUID.randomUUID();
        this.status = ACCESS_LEVEL.MENTOR;
    }

    public Mentor() {

        this("Not Available", "Not Available", "Not Available", "Not Available");
    }

    public Mentor(String firstName, String lastName) {

        this(firstName, lastName, "Not Available", "Not Available");
    }

    public Mentor(String firstName, String lastName, String email, String address, String id) {

        this(firstName, lastName, email, address);
        this.id = UUID.fromString(id);
    }
}
