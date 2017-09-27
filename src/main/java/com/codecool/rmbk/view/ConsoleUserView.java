package com.codecool.rmbk.view;

import java.util.ArrayList;
import java.util.TreeMap;

import com.codecool.rmbk.model.usr.User;


public class ConsoleUserView extends ConsoleView implements UserView {

    public String handleMenu(TreeMap<Integer,String> menu) {

        ArrayList<String> options = new ArrayList<>(menu.values());
        showMenu(options);
        return getMenuChoice(options);
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

    public String handleBrowse(TreeMap<Integer,String> menu, ArrayList users) {

        showEnumeratedList();
        System.out.println("\n");

        return handleMenu(menu);
    }

    public String handleDetails(TreeMap<Integer,String> menu, User user) {

        showShortInfo(user);
        System.out.println("\n");

        return handleMenu(menu);
    }
}
