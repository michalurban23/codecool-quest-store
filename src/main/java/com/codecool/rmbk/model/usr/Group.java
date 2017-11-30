package com.codecool.rmbk.model.usr;

import java.util.*;

public abstract class Group implements Holder {

    String name;
    Integer id;
    ArrayList<User> members;

    public Group(ArrayList<User> usersList) {

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

    public Group(Integer id, String name, ArrayList<User> members) {

        this.id = id;
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addMember(User student) {
        for (User  member : members) {
            if (member.getID() == student.getID()) {
                return;
            }
        }
        members.add(student);
    }

    public Map<String, String> getFullInfoMap() {
        Map<String, String> fullInfo = new LinkedHashMap<>();
        fullInfo.put("name", getName());
        return fullInfo;
    }

    public void clearMembersList() {
        members = new ArrayList<>();
    }

    public static List<String> getFieldLabels() {
        List<String> labels = new ArrayList<>();
        labels.add("name");
        return labels;
    }

}