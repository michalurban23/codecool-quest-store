package model.usr;

import java.util.UUID;

public class Admin extends User {

    public Admin(String firstName, String lastName, String email, String address, String id) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.id = UUID.fromString(id);
        this.status = ACCESS_LEVEL.ADMIN;
    }
}
