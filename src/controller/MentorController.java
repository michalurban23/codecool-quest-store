package controller;

import model.usr.User;

public class MentorController extends UserController{

    private UserView userView = new UserView();

    public MentorController() {;}

    public MentorController(User newUser){

        user = newUser;
    }

    public MentorController(QuestTemplate template){

        questTemplate = template;
    }

    public User createNewStudent() {

        String[] str = userView.getUserData("Student");
        return new Student(str);

    }

}
