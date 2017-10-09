package com.codecool.rmbk.model.usr;

import java.util.UUID;
import java.util.ArrayList;

public class Class extends Group {

    private UUID groupID;
    private ArrayList<Student> usersList;
    private String name;
    private static ArrayList<Class> objects = new ArrayList<>();

    public Class(ArrayList<Student> usersList) {

        this.groupID = UUID.randomUUID();
        this.usersList = usersList;
    }

    public Class() {

        this(new ArrayList<Student>());
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getUsersList() {
        return this.usersList;
    }

    public static ArrayList<Class> getObjects() {
        return objects;
    }

    public static boolean remove(Class cls) {

            return objects.remove(cls);
    }
}
