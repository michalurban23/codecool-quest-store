package com.codecool.rmbk.model.usr;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;

public class Klass extends Group {

    private UUID groupID;
    private String name;
    private Mentor mentor;
    private static ArrayList<Klass> objects = new ArrayList<>();

    public Klass(Integer id, String name) {
        super(id, name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Mentor getMentor() {
        return mentor;
    }
}
