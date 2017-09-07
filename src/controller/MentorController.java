package controller;

import model.usr.*;
import view.*;
import java.util.ArrayList;

public class MentorController extends UserController {

    private Student student = new Student();

    public MentorController() {

        userView = new ConsoleMentorView();
        user = new Mentor();
    }

    public String getUserType() {

        return "Mentor";
    }


    public void start(User mentor){
        setUser(mentor);
        boolean isBrowsed = true;
        while(isBrowsed){

            Integer choice = userView.handleMainMenu();
            if(choice == 1){
                // userView.showFullInfo(mentor);
                editUserData();
            } else if(choice == 2){
                userView.showEnumeratedList(student.getStudentsList());
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
            userView.showEnumeratedList(student.getStudentsList());
            Integer choice = userView.handleStudentMenu();
            if(choice == 1){
                Student newStudent = createNewStudent();
            } else if(choice == 2){
                System.out.println("Dupa");
            } else if(choice == 0){
                isBrowsed = false;
            }
        }
    }

    // private void handleStudentMenu(){
    //
    //     ArrayList<String> studentMenu = new ArrayList<Student>(createStudentMenu().keySet());
    //     boolean isBrowsed = true;
    //     while(isBrowsed){
    //         Integer choice = userView.getMenuChoice(studentMenu);
    //         if(choice == 1){
    //             Student newStudent = createNewStudent();
    //         } else if(choice == 2){
    //             ;
    //         } else if(choice == 0){
    //             isBrowsed = false;
    //         }
    //     }
    // }

    public Student createNewStudent() {

        String[] str = userView.getNewUserData();
        return new Student(str);
    }

}
