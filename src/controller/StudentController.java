package controller;

import model.usr.*;
import view.*;
import java.util.ArrayList;

public class StudentController extends UserController {

    ConsoleStudentView view;

    public String getUserType() {

        return "Student";
    }

    public StudentController() {
        view = new ConsoleStudentView();
    }

    public void start (User user) {
        if (user.getClass().getSimpleName().equals("Student")) {
            setUser(user);
            handleStudentMenu();
        } else {
            handleSupervisorMenu();
        }
    }

    public void handleStudentMenu() {
        boolean isBrowsed = true;
        while (isBrowsed) {
            view.clearScrean();
            view.showShortInfo(user);
            Integer choice = view.handleMainMenu();
            if (choice == 1) {
                editUserData(user);
            } else if (choice == 2) {
                QuestController questController = new QuestController();
                questController.start(user);
            } else if (choice == 3) {
                ShoppingController shoper = new ShoppingController();
                shoper.start(user);
            } else if (choice == 0) {
                isBrowsed = false;
            }
        }
    }

    private void handleSupervisorMenu() {
        boolean isBrowsed = true;
        while(isBrowsed){
            view.clearScrean();
            ArrayList<Student> students = Student.getObjects();
            Integer choice = view.handleSupervisorMenu(students);
            if(choice == 1){
                Student student = view.getListChoice(students);
                handleDetails(student);
            } else if(choice == 2){
                Student newStudent = new Student();
                editUserData(newStudent);
            } else if(choice == 0){
                isBrowsed = false;
            }
        }
    }

    private void handleDetails(Student student) {
        boolean isBrowsed = true;
        while(isBrowsed){
            view.clearScrean();
            Integer choice = view.handleDetailsMenu(student);
            if(choice == 1){
                editUserData(student);
            } else if(choice == 2){
                isBrowsed = Student.remove(student);
            } else if(choice == 0){
                isBrowsed = false;
            }
        }
    }

}
