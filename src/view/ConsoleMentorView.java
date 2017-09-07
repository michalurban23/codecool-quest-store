package view;

import java.util.LinkedHashMap;
import java.util.ArrayList;

public class ConsoleMentorView extends ConsoleUserView{

    public Integer handleMainMenu(){
        return handleMenu(createMainMenu());
    }

    private LinkedHashMap<String,Integer> createMainMenu(){

        LinkedHashMap<String,Integer> mainMenu = new LinkedHashMap<>();
        mainMenu.put("Edit account data", 1);
        mainMenu.put("Show students", 2);
        mainMenu.put("Show groups", 3);
        mainMenu.put("Show classes", 4);
        mainMenu.put("Show quest templates", 5);
        mainMenu.put("Show item templates", 6);
        mainMenu.put("Log out", 0);
        return mainMenu;
    }

    public Integer handleStudentMenu(){
        return handleMenu(createStudentMenu());
    }

    private LinkedHashMap<String,Integer> createStudentMenu(){

        LinkedHashMap<String,Integer> studentMenu = new LinkedHashMap<>();
        studentMenu.put("Add new student", 1);
        studentMenu.put("Remove student", 2);
        studentMenu.put("Edit student data", 3);
        studentMenu.put("Back", 0);
        return studentMenu;
    }
}
