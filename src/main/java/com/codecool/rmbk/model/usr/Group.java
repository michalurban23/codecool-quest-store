package com.codecool.rmbk.model.usr;

import java.util.*;

public abstract class Group implements Holder {

    String name;
    Integer id;

    public ArrayList<Student> getMembers() {
        return members;
    }

    ArrayList<Student> members;

    public Group(Integer id, String name) {

        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
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

    public void addMember(Student student) {
        for (Student  member : members) {
            if (member.getID() == student.getID()) {
                return;
            }
        }
        members.add(student);
    }

    public void clearMembersList() {
        members = new ArrayList<>();
    }

    public static List<String> getFieldLabels() {
        List<String> labels = new ArrayList<>();
        labels.add("name");
        return labels;
    }

    public void setMembers(ArrayList<Student> members) {
        this.members = members;
    }

    public Map<String,String> getMembersURIMap() {

        Map<String,String> membersURI = new LinkedHashMap<>();

        for (Student student : members) {
            membersURI.put(student.getURI(), student.getFullName());
        }

        return membersURI;
    }

    public String getURI() {
        return String.format("/%s/%s", this.getClass().getSimpleName().toLowerCase(), id);
    }

}