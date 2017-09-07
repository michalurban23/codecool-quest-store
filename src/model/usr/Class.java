package model.usr;

import java.util.UUID;

public class Class extends Group {
  private UUID classID;

  public Class() {
    classID = UUID.randomUUID();
  }
}
