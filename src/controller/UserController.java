package controller;

import model.usr.*;
import java.util.Scanner;


public abstract class UserController{

    protected User user;
    Scanner in;

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
}
