package controller;

import model.usr.*;
import view.*;

public class MentorController extends UserController {

    private UserView userView = new UserView();

    public MentorController() {;}

    public void start (User mentor) {

    }



    public Student createNewStudent() {

        String[] str = userView.getStudentInfo("Creating new Student");
        return new Student(str);
    }

    public String getUserType() {

        return "Mentor";
    }
}
