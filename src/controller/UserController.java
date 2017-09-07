package controller;

import java.util.Scanner;
import model.usr.User;
import model.usr.User.ACCESS_LEVEL;

public abstract class UserController {

    protected User user;
    Scanner in;

    public void start(User user) {;} // --------------------IMPLEMENT--------------------------

    public void editUserData(){

        editUserName();
        editUserSurname();
        editUserEmail();
        editUserAddress();
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
