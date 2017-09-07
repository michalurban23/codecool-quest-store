package model.usr;

import java.util.UUID;

public class Group implements Holder {
  private UUID groupID;
  private ArrayList<User> usersList;
  private Cart ourCart;

  public Group(ArrayList<User> usersList) {
    this.groupID = UUID.randomUUID();
    this.usersList = usersList;
  }

  public UUID getID() {
    return this.groupID;
  }

  public ArrayList<User> getUsersList() {
    return this.usersList;
  }

  public Cart getCart() {
    return this.ourCart;
  }

  public void setCart(Cart cart) {
    this.ourCart = cart;
  }

  public Quest getQuest() {;}

  public void createQuest() {;}

}
