package view;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class ConsoleUserView extends ConsoleView {

    public Integer handleMainMenu() {
        LinkedHashMap<String, Integer> menu = createMainMenu();
        ArrayList<String> options = new ArrayList<String>(menu.keySet());
        showMenu(options);
        String choice = getMenuChoice(options);
        return menu.get(choice);
    }

    public String[] getNewUserData(){
        System.out.println("Enter new user data");
        String[] labels = new String[]{"first name", "last name", "email", "address"};
        String[] newUserData = new String[4];
        for(int i = 0; i < 4; i++) {
            newUserData[i] = getString("Type new user's " + labels[i] + ": ");
        }
        return newUserData;
    }

    public abstract LinkedHashMap<String, Integer> createMainMenu();
}
