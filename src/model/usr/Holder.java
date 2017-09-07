package model.usr;

import model.Cart;
import model.quest.Quest;

public interface Holder {

    // public Cart cart;

    public Cart getCart();
    public void setCart(Cart cart);
    public Quest getQuest();
    public void createQuest();
}
