package controller;

import java.util.HashMap;
import view.ConsoleLoginView;
import view.UserView;
import view.LoginView;
import dao.CSVLoginDAO;
import dao.LoginDAO;

public class LoginController {

    private UserControllerProvider provider;
    private LoginView view;
    private LoginDAO dataAccess;
    private HashMap<String, String> loginDatabase;
    private String[] loginInfo;

    public void start() {
        provider = new UserControllerProvider();
        view = new ConsoleLoginView();
        dataAccess = new CSVLoginDAO();
        loginDatabase = dataAccess.load();
        loginInfo = logIn();
        startUserController(loginInfo[0]);
    }

    private String[] logIn() {
        String[] loginInfo = view.LoginScreen();
        while (!checkPassword()) {
            view.showWrongDataMessage();
            loginInfo = view.LoginScreen();
        }
        return loginInfo;
    }

    private boolean checkLogin(String login) {
        if (loginDatabase.containsKey(login))    {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkPassword() {
        if (!checkLogin(loginInfo[0])) {
            return false;
        } else if (!loginDatabase.get(loginInfo[0]).equals(loginInfo[1])) {
            return false;
        } else {
            return true;
        }
    }

    private void startUserController(String userLogin) {
        String userType = dataAccess.getUserTypeByLogin(userLogin);
        UserController userController = provider.getByUserType(userType);
        userController.start(dataAccess.getUserByLogin(userLogin));
    }
}
