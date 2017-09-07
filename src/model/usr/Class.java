package model.usr;

import java.util.UUID;
import java.util.ArrayList;

public class Class {

    private UUID groupID;
    private ArrayList<Student> usersList;
    private String name;
    private static ArrayList<Class> objects = new ArrayList<>();

    public Class(ArrayList<Student> usersList) {

        this.groupID = UUID.randomUUID();
        this.usersList = usersList;
        objects.add(this);
    }

    public Class() {

        this(new ArrayList<Student>());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getID() {
        return this.groupID;
    }

    public ArrayList<Student> getUsersList() {
        return this.usersList;
    }

    public static ArrayList<Class> getObjects() {
        return objects;
    }
}
