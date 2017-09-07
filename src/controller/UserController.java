package controller;
import model.usr.User;
import java.util.Scanner;

public abstract class UserController{

    protected User user;
    protected UserConsoleView userView;

    public void editUserData() {
        String[] newData = userView.getNewUserData();

        if (!newData[0] == null) {
            user.setFirstName(newData[0]);
        } else if (!newData[1] == null) {
            user.setLastName(newData[1]);
        } else if (!newData[2] == null) {
            user.setEmail(newData[2]);
        } else if (!newData[3] == null) {
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
}
