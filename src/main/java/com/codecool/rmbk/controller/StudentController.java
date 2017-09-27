package com.codecool.rmbk.controller;


public class StudentController extends UserController {

    public String getUserType() {
        return "Student";
    }

    public StudentController() {
        super();
    }
}
