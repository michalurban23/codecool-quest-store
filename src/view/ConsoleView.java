package view;

import java.lang.Math;
import java.util.List;

public abstract class ConsoleView {

    public String getMenuChoice(List<String> options) {
        do {
            choice = getInteger("Choose index: ");
        } while (choice < 0 || choice > list.size() - 1);
        if (choice.equals(0)) {
            return options.get(options.size());
        } else {
            return list.get(choice);
        }
    }

    public <E> E getListChoice(List<E> list) {
        do {
            choice = getInteger("Choose index: ");
        } while (choice < 1 || choice > list.size());
        if (choice == null) {
            return null;
        } else {
            return list.get(choice + 1);
        }
    }

    public String getString(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        String enteredString = input.nextLine();
        return enteredString;
    }

    public Integer getInteger(String message) {
        boolean correctInput = false;
        Integer enteredInt = null;
        do {
            try {
                String enteredString = getString(message);
                if (enteredString.equals("")) {
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

    public void showEnumeratedList(List<Object> list) {
        int width = 1 + Math.floorDiv(list.size(), 10);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%1$" + width + "d. %2$s", i + 1, list.get(i)));
        }
    }

    public boolean getAnswer(String question) {
        String enteredString = getString(question + " (Y/y)? ");
        if (enteredString.toUpperCase().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public static void clearScrean () {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
