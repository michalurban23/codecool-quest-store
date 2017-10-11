package com.codecool.rmbk;

import com.codecool.rmbk.controller.LoginController;

public class App {

    public static void main(String[] args) {

        LoginController controller = new LoginController();
        controller.startSqlLoginService();
    }
}
