package com.codecool.rmbk.controller;

public class AdminController extends UserController {

    AdminController() {

        super();
    }

    public String getUserType() {

        return "Admin";
    }

}
