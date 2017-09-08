package view;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import model.usr.Mentor;

public class ConsoleMentorView extends ConsoleUserView {

    public Integer handleMainMenu(){
        return handleMenu(createMainMenu());
    }

    protected LinkedHashMap<String,Integer> createMainMenu(){

        LinkedHashMap<String,Integer> mainMenu = new LinkedHashMap<>();
        mainMenu.put("Edit account data", 1);
        mainMenu.put("Students", 2);
        mainMenu.put("Groups", 3);
        mainMenu.put("My Classes", 4);
        mainMenu.put("Quests", 5);
        mainMenu.put("Artifacts", 6);
        mainMenu.put("Log out", 0);
        return mainMenu;
    }

    public <E> Integer handleSupervisorMenu(ArrayList<E> mentors) {
        showEnumeratedList(mentors);
        System.out.println("\n");
        return handleMenu(createSupervisorMenu());
    }

    private LinkedHashMap<String,Integer> createSupervisorMenu(){

        LinkedHashMap<String,Integer> studentMenu = new LinkedHashMap<>();
        studentMenu.put("Choose mentor", 1);
        studentMenu.put("Add mentor", 2);
        studentMenu.put("Back", 0);
        return studentMenu;
    }

    public Integer handleDetailsMenu(Mentor mentor) {
        showShortInfo(mentor);
        System.out.println("\n");
        return handleMenu(createDetailsMenu());
    }

    private LinkedHashMap<String,Integer> createDetailsMenu(){
        LinkedHashMap<String,Integer> studentMenu = new LinkedHashMap<>();
        studentMenu.put("Edit mentor data", 1);
        studentMenu.put("Remove mentor", 2);
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
