package models.usr;

import java.util.UUID;

public abstract class User {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String address;
    protected final UUID id = UUID.randomUUID();
    protected ACCESS_LEVEL status;

    protected enum ACCESS_LEVEL {
        ADMIN,
        MENTOR,
        STUDENT
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

    public UUID getID() {

        return this.id;
    }

    public String getFullName() {

        return this.firstName + " " + this.lastName;
    }

    public ACCESS_LEVEL getStatus() {

        return this.status;
    }

    public String toString() {

        return this.id + "\n"
                + this.firstName + " " + this.lastName
                + "\nEmail address: " + this.email
                + "\nHome address: " + this.address;
    }
}
