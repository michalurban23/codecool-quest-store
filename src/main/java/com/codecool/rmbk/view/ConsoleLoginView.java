package com.codecool.rmbk.view;

public class ConsoleLoginView extends ConsoleView implements LoginView {

    public ConsoleLoginView() {}

    public String[] LoginScreen() {

        String[] loginData = new String[2];

        clearScreen();
        loginData[0] = getString("Enter login: ");
        loginData[1] = getString("Enter password: ");

        return loginData;
    }

    public void showWrongDataMessage() {

        System.out.println("Invalid username or password");
        pause(1);
    }
}
