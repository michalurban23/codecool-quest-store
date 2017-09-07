package models.usr;

import java.util.UUID;

public class Group {
  private UUID groupID;
  private ArrayList<User> usersList;

  public Group(ArrayList<User> usersList) {
    groupID = UUID.randomUUID();
    usersList = usersList;
  }

  private UUID getID() {
    return groupID;
  }

  private ArrayList<User> getUsersList() {
    return usersList;
  }

}
