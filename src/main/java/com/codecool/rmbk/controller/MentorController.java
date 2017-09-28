package com.codecool.rmbk.controller;

import java.util.ArrayList;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.model.usr.Mentor;

public class MentorController extends UserController {

    public MentorController() {
        super();
    }

    public String getUserType() {
        return "Mentor";
    }

}
