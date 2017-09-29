package com.codecool.rmbk.view;

import java.util.*;

public abstract class ConsoleView {

    private Scanner input = new Scanner(System.in);
    private static TreeMap<String, String> colors = fillColors();

    private static TreeMap<String, String> fillColors() {

        TreeMap<String,String> colors = new TreeMap<>();

        colors.put("RED", "\033[1;31m");
        colors.put("GREEN", "\033[0;32m");
        colors.put("YELLOW", "\033[1;33m");
        colors.put("RESET", "\033[0;0m");

        return colors;
    }

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

    public String getInput(String message) {

        System.out.print("\n" + message);
        return input.nextLine();
    }

    public <E> E getListChoice(List<E> list) {

        Integer choice = null;

        do {
            choice = getInteger("Choose index: ");
            if (choice == null) {
                return null;
            }
        } while (choice < 1 || choice > list.size());

        return list.get(choice);
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

    public void showMenu(String title, TreeMap<Integer, String> menu) {

        clearScreen();
        System.out.printf("* * * %s * * *%n%n", title);

        for (Map.Entry<Integer, String> entry : menu.entrySet()) {
            System.out.printf("%d) %s%n", entry.getKey(), entry.getValue());
        }

        System.out.println("--");
        System.out.println("0) Return / Exit");
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

    public void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void pause(int seconds) {

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {}
    }

    public void pause() {

        System.out.println("\nPress Enter to continue\n");
        input.nextLine();
    }

    public void printMessage(String message) {

        System.out.println("\n" + message);
    }

    public void printWarning(String message) {

        String yellow = colors.get("YELLOW");
        String reset = colors.get("RESET");

        System.out.println("\n" + yellow + message + reset);
    }

    public void printError(String message) {

        String red = colors.get("RED");
        String reset = colors.get("RESET");

        System.out.println("\n" + red + message + reset);
    }
}
