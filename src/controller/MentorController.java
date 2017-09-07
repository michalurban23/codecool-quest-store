package controller;

import model.usr.Student;
import view.UserView;

public class MentorController extends UserController{

    private UserView userView = new UserView();

    public MentorController() {;}

    // public MentorController(User newUser){
    //
    //     user = newUser;
    // }
    //
    // public MentorController(QuestTemplate template){
    //
    //     questTemplate = template;
    // }

    public Student createNewStudent() {

        String[] str = userView.getStudentInfo("Creating new Student");
        return new Student(str);
    }

    public String getUserType() {

        return "Mentor";
    }
}
