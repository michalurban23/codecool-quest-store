package controller;

import model.usr.*;
import view.*;

public class MentorController extends UserController {

    public MentorController() {

        view = new MentorConsoleView();
    }

    public Student createNewStudent() {

        String[] str = view.getNewUserData();
        return new Student(str);
    }

    public void start(User user){

        Integer choice = view.handleMainMenu();
        if(choice == 1){
            editUserData(user);
        } else if(choice == 2){
            ;
        } else if(choice == 3){
            ;
        } else if(choice == 4){
            ;
        } else if(choice == 5){
            ;
        } else if(choice == 6){
            ;
        } else if(choice == 0){
            ;
        }
    }


    public String getUserType() {

        return "Mentor";
    }
}
