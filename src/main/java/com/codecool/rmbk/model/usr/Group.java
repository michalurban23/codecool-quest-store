package com.codecool.rmbk.model.usr;

import java.util.ArrayList;

public abstract class Group {

    String name;
    Integer id;
    ArrayList<Student> members;

    public Group(ArrayList<Student> usersList) {

        this.members = usersList;
        name = "";
    }

    public Group() {

        this.members = new ArrayList<>();
        name = "";
    }

    public Group(Integer id, String name) {

        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Integer getID() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addMember(Student student) {
        for (Student member : members) {
            if (member.getID() == student.getID()) {
                return;
            }
        }
        members.add(student);
    }

    public ArrayList<Student> getMembers() {
        return members;
    }

    public void clearMembersList() {
        members = new ArrayList<>();
    }
}