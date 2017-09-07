package view;

import java.util.LinkedHashMap;
import java.util.ArrayList;

public class ConsoleStudentView extends ConsoleUserView {

    public Integer handleMainMenu() {
        return handleMenu(createMainMenu())
    }

    public LinkedHashMap<String, Integer> createMainMenu() {

        LinkedHashMap<String, Integer> mainMenu = new LinkedHashMap<>();
        mainMenu.put("Edit account data", 1);
        mainMenu.put("Quests", 2);
        mainMenu.put("Artifacts", 3);
        mainMenu.put("Log out", 0);
        return mainMenu;
    }

}
