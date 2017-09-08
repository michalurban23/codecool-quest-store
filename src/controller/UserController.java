package controller;

import java.util.Scanner;
import model.usr.User;
import view.ConsoleUserView;

public abstract class UserController {

    User user;
    ConsoleUserView view;

    public abstract void start(User user);

    public void setUser(User user) {

        this.user = user;
    }

    public void editUserData(User user) {
        String[] newData = view.getNewUserData();

        if (newData[0] != null) {
            user.setFirstName(newData[0]);
        }
        if (newData[1] != null) {
            user.setLastName(newData[1]);
        }
        if (newData[2] != null) {
            user.setEmail(newData[2]);
        }
        if (newData[3] != null) {
            user.setAddress(newData[3]);
        }
    }

    public abstract String getUserType();

}
