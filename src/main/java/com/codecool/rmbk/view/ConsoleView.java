package com.codecool.rmbk.view;

import java.util.ArrayList;
import java.util.Collections;
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

    public Boolean printList(String title, ArrayList<ArrayList<String>> data) {

        if (data.size() <= 1) {
            printWarning("No matching data in " + title);
            return false;
        }

        ArrayList<String> labels = data.get(0);
        ArrayList<Integer> widths = calculateWidths(data); // last element is a total table width

        String horizontalLine = String.join("", Collections.nCopies(widths.get(widths.size()-1), "-"));

        System.out.printf("%n> > > %s < < <%n%n", title);

        for (int i = 0; i < data.size(); i++) {

            ArrayList<String> entry = data.get(i);
            String index = i==0 ? "#" : String.valueOf(i);

            if (i == 1) {
                System.out.println(horizontalLine);
            }
            System.out.printf("| %2s |", index);

            for(int column = 0; column < labels.size(); column++) {
                int width = widths.get(column);
                System.out.printf(" %" + width + "s |", entry.get(column));
            } System.out.println();
        }
        System.out.println(horizontalLine + "\n");

        return true;
    }

    private ArrayList<Integer> calculateWidths(ArrayList<ArrayList<String>> data) {

        ArrayList<Integer> widths = new ArrayList<>();
        Integer totalWidth = 0;
        int columnsNumber = data.get(0).size();
        int totalOffset = 6 + columnsNumber * 3; // Total padding of all columns + width of index column

        for (int i=0; i < columnsNumber; i++) {

            Integer longest = data.get(0).get(i).length();

            for (ArrayList<String> row : data) {

                Integer currentWidth;
                String current = row.get(i);
                if (current != null) {      // This is to account for any entry being literally "null"
                    currentWidth = current.length();
                } else {
                    currentWidth = 4; // "Length" of null
                }

                if (currentWidth > longest) {
                    longest = currentWidth;
                }
            }
            widths.add(longest);
            totalWidth += longest;
        }
        widths.add(totalWidth + totalOffset);
        return widths;
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

    public void printWarning(String message) {

        System.err.println(message);
    }

}
