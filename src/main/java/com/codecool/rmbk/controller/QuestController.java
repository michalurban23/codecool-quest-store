package com.codecool.rmbk.controller;

import com.codecool.rmbk.model.usr.User;

import java.util.TreeMap;

public class QuestController {

    public void start (User user) {

        if (user.getStatus().equals("student")) {
            runStudentMenu();
        } else {
            runMentorMenu();
        }
    }

    private void runStudentMenu() {;}

    private void runMentorMenu() {;}

    private TreeMap<Integer, String> getStudentMenu() {return null;}

    private TreeMap<Integer, String> getMentorMenu() {

        TreeMap<Integer, String> menu = new TreeMap<>();

        menu.put(1, "Show all available quest templates");
        menu.put(2, "Add new template");
        menu.put(3, "Edit a template");
        menu.put(4, "Remove a template");

        return menu;
    }
}
