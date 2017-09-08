package view;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import model.usr.Student;


public class ConsoleStudentView extends ConsoleUserView {

    public Integer handleMainMenu() {
        return handleMenu(createMainMenu());
    }

    public LinkedHashMap<String, Integer> createMainMenu() {

        LinkedHashMap<String, Integer> mainMenu = new LinkedHashMap<>();
        mainMenu.put("Edit account data", 1);
        mainMenu.put("Quests", 2);
        mainMenu.put("Artifacts", 3);
        mainMenu.put("Log out", 0);
        return mainMenu;
    }

    public <E> Integer handleSupervisorMenu(ArrayList<E> students){
        showEnumeratedList(students);
        System.out.println("\n");
        return handleMenu(createSupervisorMenu());
    }

    private LinkedHashMap<String,Integer> createSupervisorMenu(){
        LinkedHashMap<String,Integer> studentMenu = new LinkedHashMap<>();
        studentMenu.put("Choose student", 1);
        studentMenu.put("Add new student", 2);
        studentMenu.put("Back", 0);
        return studentMenu;
    }

    public Integer handleDetailsMenu(Student student) {
        showShortInfo(student);
        System.out.println("\n");
        return handleMenu(createDetailsMenu());
    }

    private LinkedHashMap<String,Integer> createDetailsMenu(){
        LinkedHashMap<String,Integer> studentMenu = new LinkedHashMap<>();
        studentMenu.put("Edit student data", 1);
        studentMenu.put("Remove student", 2);
        studentMenu.put("Back", 0);
        return studentMenu;
    }

}
