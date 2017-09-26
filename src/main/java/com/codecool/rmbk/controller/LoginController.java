package com.codecool.rmbk.controller;

import com.codecool.rmbk.view.LoginView;
import com.codecool.rmbk.view.ConsoleLoginView;
import com.codecool.rmbk.dao.*;

public class LoginController {

    private UserControllerProvider provider;
    private LoginView view;
    private LoginDAO dataAccess;
    private String[] loginInfo;

    public LoginController() {

        provider = new UserControllerProvider();
        view = new ConsoleLoginView();
    }

    public void startCSV() {

        dataAccess = new CSVLoginDAO();
        // loginDatabase = dataAccess.start();
        loginInfo = logIn();
        startUserController(loginInfo[0]);
    }

    public void startSQL() {

        dataAccess = new SQLLoginDAO();
        dataAccess.start();

        loginInfo = view.LoginScreen();

        if (dataAccess.login(loginInfo)) {
            startUserController(loginInfo[0]);
        }
    }

    private void startUserController(String userLogin) {

        CSVUserInfoDAO userInfo = new CSVUserInfoDAO();

        String userType = userInfo.getUserTypeByLogin(userLogin);
        UserController userController = provider.getByUserType(userType);
        userController.start(userInfo.getUserByLogin(userLogin));
    }
}
