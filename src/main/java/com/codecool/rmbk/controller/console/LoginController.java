package com.codecool.rmbk.controller.console;

import com.codecool.rmbk.view.console.LoginView;
import com.codecool.rmbk.view.console.ConsoleLoginView;
import com.codecool.rmbk.dao.*;

public class LoginController {

    private UserControllerProvider provider;
    private LoginView view;
    private UserInfoDAO userDao;
    private Boolean mainAppStarted = false;

    public LoginController() {

        provider = new UserControllerProvider();
        view = new ConsoleLoginView();
    }

    public void startSqlLoginService() {

        SQLLoginDAO.setPermission();
        SQLLoginDAO dataAccess = new SQLLoginDAO();
        userDao = new SQLUsers();

        while (!mainAppStarted) {
            String[] loginInfo = view.LoginScreen();

            if (dataAccess.login(loginInfo[0], loginInfo[1])) {
                startUserController(loginInfo[0]);
            } else {
                view.showWrongDataMessage();
            }
        }
    }

    private void startUserController(String userLogin) {

        mainAppStarted = true;

        String userType = userDao.getUserTypeByLogin(userLogin);
        UserController userController = provider.getByUserType(userType);
        userController.start(userDao.getUserByLogin(userLogin));
    }

}
