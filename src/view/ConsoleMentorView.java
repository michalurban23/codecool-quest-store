package view;

import java.util.LinkedHashMap;
import java.util.ArrayList;

public class ConsoleMentorView extends ConsoleUserView{

    public Integer handleMainMenu(){
        return handleMenu(createMainMenu());
    }

    protected LinkedHashMap<String,Integer> createMainMenu(){

        LinkedHashMap<String,Integer> mainMenu = new LinkedHashMap<>();
        mainMenu.put("Edit account data", 1);
        mainMenu.put("Students", 2);
        mainMenu.put("Groups", 3);
        mainMenu.put("Classes", 4);
        mainMenu.put("Quest templates", 5);
        mainMenu.put("Item templates", 6);
        mainMenu.put("Log out", 0);
        return mainMenu;
    }

    public Integer handleStudentOption(){
        return handleMenu(createStudentMenu());
    }

    private LinkedHashMap<String,Integer> createStudentMenu(){

        LinkedHashMap<String,Integer> studentMenu = new LinkedHashMap<>();
        studentMenu.put("Add new student", 1);
        studentMenu.put("Choose student", 2);
        studentMenu.put("Back", 0);
        return studentMenu;
    }

    public Integer getManageStudentOption(){
        return handleMenu(manageStudentsMenu());
    }

    private LinkedHashMap<String,Integer> manageStudentsMenu(){

        LinkedHashMap<String,Integer> addOrRemoveMenu = new LinkedHashMap<>();
        addOrRemoveMenu.put("Edit student's data", 1);
        addOrRemoveMenu.put("Remove student", 2);
        addOrRemoveMenu.put("Back", 0);
        return addOrRemoveMenu;
    }
}
