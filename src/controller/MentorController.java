package controller;

import model.usr.*;
import view.*;

public class MentorController extends UserController {

    private UserView userView = new UserView();

    public MentorController() {

        userView = new MentorConsoleView;
    }

    public void start (User mentor) {

    }



    public Student createNewStudent() {

        String[] str = userView.getNewUserData("Creating new Student");
        return new Student(str);
    }

    public void start(User mentor){

        Integer choice = userView.handleMainMenu();
        if(choice == 1){
            editAccountData(mentor);
        } else if(choice == 2){
            showStudents();
        } else if(choice == 3){
            showStudents();
        } else if(choice == 4){
            showStudents();
        } else if(choice == 5){
            showStudents();
        } else if(choice == 6){
            showStudents();
        } else if(choice == 0){
            showStudents();
    }

    private editAccountData(User mentor){

        ;
    }


    public String getUserType() {

        return "Mentor";
    }
}
