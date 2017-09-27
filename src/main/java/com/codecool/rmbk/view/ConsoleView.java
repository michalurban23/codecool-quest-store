package com.codecool.rmbk.view;

import java.lang.Math;
import java.lang.Thread;
import java.util.List;
import java.util.Scanner;

public abstract class ConsoleView {

    public String getMenuChoice(List<String> options) {

        Integer choice = null;

        do {
            choice = getInteger("Choose index: ");
        } while (choice == null || choice < 0 || choice > options.size() - 1);

        if (choice.equals(0)) {
            return options.get(options.size() - 1);
        } else {
            return options.get(choice - 1);
        }
    }

    public <E> E getListChoice(List<E> list) {

        Integer choice = null;

        do {
            choice = getInteger("Choose index: ");
            if (choice == null) {
                return null;
            }
        } while (choice < 1 || choice > list.size());

        return list.get(choice - 1);
    }

    public String getString(String message) {

        Scanner input = new Scanner(System.in);
        System.out.println(message);
        String enteredString = input.nextLine();

        if (enteredString.length() == 0) {
            enteredString = null;
        }

        return enteredString;
    }

    public Integer getInteger(String message) {

        boolean correctInput = false;
        Integer enteredInt = null;

        do {
            try {
                String enteredString = getString(message);
                if (enteredString == null) {
                    enteredInt = null;
                } else {
                    enteredInt = new Integer(enteredString);
                }
                correctInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong number!");
            }
        } while(!correctInput);

        return enteredInt;
    }

    public void showMenu(List<String> options) {

        int indexWidth = 1 + Math.floorDiv(options.size() - 1, 10);
        showEnumeratedList(options.subList(0, options.size() - 1));
        System.out.println(String.format("%1$" + indexWidth + "d. %2$s", 0, options.get(options.size() - 1)));

    }

    public <E> void showEnumeratedList(List<E> list) {

        int width = 1 + Math.floorDiv(list.size(), 10);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%1$" + width + "d. %2$s", i + 1, list.get(i)));
        }
    }

    public boolean getAnswer(String question) {

        String enteredString = getString(question + " (Y/y)? ");

        return enteredString.toUpperCase().equals("Y");
    }

    public static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void pause(int seconds) {

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {}
    }

}