package com.codecool.rmbk.view.console;

import java.io.Console;

public class ConsoleLoginView extends ConsoleView implements LoginView {

    public String[] LoginScreen() {

        String[] loginData = new String[2];

        clearScreen();
        loginData[0] = getString("Enter login: ");
        loginData[1] = getString("Enter password: "); // <-- IntelliJ login
        // loginData[1] = getPass("Enter password: ");  // <-- console login

        return loginData;
    }

    public void showWrongDataMessage() {

        System.out.println("Invalid username or password");
        pause(1);
    }

    private String getPass(String message) {

        char[] password = new char[] {'p','a','s','s'};

        try {
            Console console = System.console();
            if (console != null) {
                password = console.readPassword(message);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return String.valueOf(password);
    }
}
