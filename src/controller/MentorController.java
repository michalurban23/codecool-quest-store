package controller;

import model.usr.Student;
import view.UserView;

public class MentorController extends UserController {

    private UserView userView = new UserView();

    public MentorController() {;}

    public void start (User mentor) {
        
    }



    public Student createNewStudent() {

        String[] str = userView.getStudentInfo("Creating new Student");
        return new Student(str);

    }

}
