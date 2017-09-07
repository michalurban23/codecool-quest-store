package controller;

import model.usr.User;
import model.usr.Mentor;

public class AdminController extends UserController{

  private UserView userView = new UserView();

  public AdminController() {;}

  public AdminController(User newUser) {
    user = newUser;
  }

  public Class createClass() {
    Class newClass = new Class();
    return newClass;
  }

  public User createNewMentor() {

    String[] info = userView.getUserData("Mentor");
    return new Mentor();
  }

  public void removeMentor(Mentor mentor) {;}

}
