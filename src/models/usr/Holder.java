package models.usr;

import models.Cart;
import models.quest.Quest;

public interface Holder {

    // public Cart cart;

    public Cart getCart();
    public void setCart(Cart cart);
    public Quest getQuest();
    public void createQuest();
}
