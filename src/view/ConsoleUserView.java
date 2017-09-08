package view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import model.usr.User;

public abstract class ConsoleUserView extends ConsoleView implements UserView {

    public Integer handleMenu(LinkedHashMap<String,Integer> menu) {

        ArrayList<String> options = new ArrayList<String>(menu.keySet());
        showMenu(options);
        String choice = getMenuChoice(options);

        return menu.get(choice);
    }

    public void showShortInfo(User user) {

        System.out.println(user.getClass().getSimpleName());
        System.out.println(user.getFirstName() + " " + user.getLastName());
    }

    public void showFullInfo(User user) {

        System.out.println("First name: " + user.getFirstName());
        System.out.println("Last name: " + user.getLastName());
        System.out.println("E-mail: " + user.getEmail());
        System.out.println("Adress: " + user.getAddress());
    }

    public String[] getNewUserData(){

        System.out.println("\nEnter new user data");
        String[] labels = new String[]{"first name", "last name", "email", "address"};
        String[] newUserData = new String[4];

        for(int i = 0; i < 4; i++) {
            newUserData[i] = getString("Type new user's " + labels[i] + ": ");
        }
        return newUserData;
    }

    protected abstract LinkedHashMap<String, Integer> createMainMenu();
}
