import java.util.LinkedHashMap;
import java.util.ArrayList;

public class MentorConsoleView extends UserConsoleView implements UserView{

    public MentorConsoleView(){
        ;
    }

    private static LinkedHashMap<String,Integer> createMainMenu(){

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

    }
}
