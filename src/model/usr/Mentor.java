package model.usr;

import java.util.UUID;
import java.util.ArrayList;

public class Mentor extends User {

    private static ArrayList<Mentor> objects = new ArrayList<Mentor>();

    public Mentor(String firstName, String lastName, String email, String address) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.id = UUID.randomUUID();
        this.status = ACCESS_LEVEL.MENTOR;
        objects.add(this);
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

    public static ArrayList<Mentor> getObjects(){
        return objects;
    }

    public static boolean remove(User user) {
        if (objects.contains(user)) {
            objects.remove(user);
            return true;
        } else {
            return false;
        }
    }
}
