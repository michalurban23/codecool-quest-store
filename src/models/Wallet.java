package models;

public class Wallet {
  private int coins;
  private int level;
  private String log;

  public Wallet(int coins, int level, String log) {
    this.coins = coins;
    this.level = level;
    this.log = log;
  }

  public Wallet() {;}

  private int getCoins() {
    return this.coins;
  }

  private int getLevel() {
    return this.level;
  }

  private void printLog() {;}

  private void showWallet() {
    System.out.println("Piniondze :: " + this.coins.toString());
    System.out.println("Level :: " + this.level.toString());
  }

}
