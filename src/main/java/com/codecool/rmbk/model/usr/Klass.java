package com.codecool.rmbk.model.usr;

import java.util.UUID;
import java.util.ArrayList;

public class Klass extends Group {

    private UUID groupID;
    private ArrayList<Student> usersList;
    private String name;
    private static ArrayList<Klass> objects = new ArrayList<>();

    public Klass(ArrayList<Student> usersList) {

        this.groupID = UUID.randomUUID();
        this.usersList = usersList;
    }

    public Klass() {

        this(new ArrayList<Student>());
    }

    public Klass(Integer id, String name) {

        super(id, name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getUsersList() {
        return this.usersList;
    }

    public static ArrayList<Klass> getObjects() {
        return objects;
    }

    public static boolean remove(Klass cls) {

            return objects.remove(cls);
    }
}
