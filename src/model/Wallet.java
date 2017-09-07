package model;

import java.util.*;

public class Wallet {
  private int coins;
  private int level;
  private ArrayList<String> log;

  public Wallet(int coins, int level, ArrayList<String> log) {
    this.coins = coins;
    this.level = level;
    this.log = log;
  }

  public int getCoins() {
    return this.coins;
  }

  public int getLevel() {
    return this.level;
  }

  public ArrayList<String> getLog() {
    return this.log;
  }

  public void setCoins(int coins) {
    this.coins = coins;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setLog(ArrayList<String> log) {
    this.log = log;
  }
}
