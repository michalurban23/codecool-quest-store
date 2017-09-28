package com.codecool.rmbk.controller;

public class AdminController extends UserController {

    public AdminController() {
        super();
    }

    public String getUserType() {
        return "Admin";
    }
}
