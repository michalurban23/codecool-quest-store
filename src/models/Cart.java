package src.models;

import java.util.*;

public class Cart {
  private ArrayList<Object> itemList;
  private boolean group;

  public Cart(ArrayList<Object> itemList, boolean group) {
    this.itemList = itemList;
    this.group = group;
  }

  private Cart() {;}

  private void addToCart(Object item) {
    this.itemList.add(item);
  }

  private void removeFromCart(Object item) {
    for(int i = 0; i < this.itemList.size(); i++) {
      if(this.itemList.get(i).equals(item)) {
        this.itemList.remove(i);
        break;
      }
    }
  }

  private void showCart() {
    for(int i = 0; i < this.itemList.size(); i++) {
      System.out.println(this.itemList.get(i));
    }
  }

  private void flushCart() {
    this.itemList.clear();
  }
}
