package controller;

import model.usr.*;
import view.*;
import java.util.ArrayList;

public class MentorController extends UserController {

    private Student student;
    ConsoleMentorView view;

    public MentorController() {

        view = new ConsoleMentorView();
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
                editUserData(mentor);
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
            Integer choice = view.handleStudentOption();
            Student newStudent;
            if(choice == 1){
                newStudent = new Student(view.getNewUserData());
            } else if(choice == 2){
                // view.showEnumeratedList(student.getStudentsList());
                newStudent = view.getListChoice(student.getStudentsList());
                System.out.println(student);
                manageStudentOption(student);
            } else if(choice == 0){
                isBrowsed = false;
            }
        }
    }

    private void manageStudentOption(User user){

        boolean isBrowsed = true;
        while(isBrowsed){
            Integer choice = view.getManageStudentOption();
            if(choice == 1){
                editUserData(student);
            } else if(choice == 2){
                student.getStudentsList().remove(student);
            } else if(choice == 0){
                isBrowsed = false;
            }
        }
    }


}
