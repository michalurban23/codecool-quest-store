package controller;

import model.usr.*;
import view.*;
import java.util.ArrayList;

public class MentorController extends UserController {

    private Student student = new Student();
    ConsoleMentorView view;

    public MentorController() {

        this.view = new ConsoleMentorView();
        super.view = new ConsoleMentorView();
    }

    public String getUserType() {

        return "Mentor";
    }


    public void start(User mentor){
        setUser(mentor);
        boolean isBrowsed = true;

        while(isBrowsed){

            Integer choice = view.handleMainMenu();
            if(choice == 1){
                // userView.showFullInfo(mentor);
                editUserData();
            } else if(choice == 2){
                handleStudentOption();
            } else if(choice == 3){
                ;
            } else if(choice == 4){
                ;
            } else if(choice == 5){
                ;
            } else if(choice == 6){
                ;
            } else if(choice == 0){
                isBrowsed = false;
            }
        }
    }

    private void handleStudentOption(){

        boolean isBrowsed = true;
        while(isBrowsed){
            view.showEnumeratedList(student.getStudentsList());
            Integer choice = view.handleStudentOption();
            if(choice == 1){
                Student newStudent = createNewStudent();
            } else if(choice == 2){
                ;
            } else if(choice == 0){
                isBrowsed = false;
            }
        }
    }

    public Student createNewStudent() {

        String[] str = view.getNewUserData();
        return new Student(str);
    }

}
