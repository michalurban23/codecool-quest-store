package models;

import java.util.*;

public class Wallet {
  private int coins;
  private int level;
  private List<String> log;

  public Wallet(int coins, int level, String log) {
    coins = coins;
    level = level;
    log = log;
  }

  private int getCoins() {
    return coins;
  }

  private int getLevel() {
    return level;
  }

  private List<String> getLog() {
    return log;
  }

  private void setCoins(int coins) {
    coins = coins;
  }

  private void setLevel(int level) {
    level = level;
  }

  private void setLog(List<String> log) {
    log = log;
  }
}
