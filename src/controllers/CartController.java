package controllers;

import src.view.*;
import src.models.Cart;

public class CartController {
  private boolean proceed = false;
  private Cart cart;

  private void showCart() {
    cart.showCart();
  }

  private void process() {
    checkout();
    if(proceed) {
      pay();
    }
  }

  private void checkout() {
    String confirmation = Display.getInput("Are you sure you want to continue? (y/n)");
    if(confirmation.equals("y")) {
      proceed = true;
    }
  }

  private void pay() {
    cart.flushCart();
    Display.printMessage("Payment was succesful!");
  }
}
