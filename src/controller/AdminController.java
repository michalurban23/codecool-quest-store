package controller;

import model.usr.User;
import model.usr.Mentor;
import model.usr.Class;
import view.UserView;

public class AdminController extends UserController {

  private UserView userView = new UserView();

  public AdminController() {;}

  public void start (User admin) {}

  public Class createClass() {
    Class newClass = new Class();   
    return newClass;
  }

  public User createNewMentor() {

    String[] info = userView.getNewUserData("Mentor");
    return new Mentor();
  }

  public void removeMentor(Mentor mentor) {;}

  public String getUserType() {

      return "Admin";
  }
}
