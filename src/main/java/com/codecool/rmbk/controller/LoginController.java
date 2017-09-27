package com.codecool.rmbk.controller;

import com.codecool.rmbk.view.LoginView;
import com.codecool.rmbk.view.ConsoleLoginView;
import com.codecool.rmbk.dao.*;

public class LoginController {

    private UserControllerProvider provider;
    private LoginView view;
    private LoginDAO dataAccess;
    private String[] loginInfo;
    private Boolean appRunning = false;

    public LoginController() {

        provider = new UserControllerProvider();
        view = new ConsoleLoginView();
    }

    public void startCSV() {

        dataAccess = new CSVLoginDAO();
        // loginDatabase = dataAccess.start();
        // loginInfo = logIn();
        startUserController(loginInfo[0]);
    }

    public void startSqlLoginService() {

        dataAccess = new SQLLoginDAO();

        while (!appRunning) {
            loginInfo = view.LoginScreen();
            if (dataAccess.login(loginInfo)) {
                startUserController(loginInfo[0]);
            } else {
                view.showWrongDataMessage();
            }
        }
    }

    private void startUserController(String userLogin) {

        appRunning = true;
        CSVUserInfoDAO userInfo = new CSVUserInfoDAO();

        String userType = userInfo.getUserTypeByLogin(userLogin);
        UserController userController = provider.getByUserType(userType);
        userController.start(userInfo.getUserByLogin(userLogin));
    }

}
