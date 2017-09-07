package controller;

import java.util.Scanner;
import model.usr.*;
import view.*;
import model.usr.User.ACCESS_LEVEL;

public abstract class UserController {

    protected User user;
    protected ConsoleUserView view;

    public abstract void start(User user);

    public void setUser(User user) {
        this.user = user;
    }

    public void editUserData() {
        view.showFullInfo(user);
        String[] newData = view.getNewUserData();
        if (!(newData[0] == null)) {
            user.setFirstName(newData[0]);
        }
        if (!(newData[1] == null)) {
            user.setLastName(newData[1]);
        }
        if (!(newData[2] == null)) {
            user.setEmail(newData[2]);
        }
        if (!(newData[3] == null)) {
            user.setAddress(newData[3]);
        }
    }

    public void editUserName(){
        ;
    }

    public void editUserSurname(){
        ;
    }

    public void editUserEmail(){
        ;
    }

    public void editUserAddress(){
        ;
    }

    public abstract String getUserType();

}
