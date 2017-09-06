package models.item;

import models.usr.Holder;
import java.time.LocalDateTime;

public class Item {

    private Holder owner;
    private LocalDateTime buyTime;

    public Item(Holder owner) {

        this.owner = owner;
    }

    public Holder getOwner() {

        return this.owner;
    }

    public void setOwner(Holder owner) {

        this.owner = owner;
    }

    public LocalDateTime getBuyTime() {

        return this.buyTime;
    }

    public void setBuyTime(LocalDateTime buyTime) {

        this.buyTime = buyTime;
    }
}
