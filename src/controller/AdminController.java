package controller;

import model.usr.User;
import model.usr.Mentor;
import model.usr.Class;
import view.*;

public class AdminController extends UserController {

    public AdminController() {;}

    public void start (User admin) {}

    public Class createClass() {
        Class newClass = new Class();
        return newClass;
    }

    public User createNewMentor() {

        String[] info = userView.getNewUserData();
        return new Mentor();
    }

    public void removeMentor(Mentor mentor) {;}

    public String getUserType() {

        return "Admin";
    }
}
