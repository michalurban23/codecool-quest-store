package model.item;

import java.util.UUID;
import model.usr.Holder;
import java.time.LocalDateTime;

public class Item {

    private Holder owner;
    private LocalDateTime buyTime;
    private UUID id;
    // private String category = ItemTemplate.getCategory().BASIC;

    public Item(Holder owner) {

        this.id = UUID.randomUUID();
        this.owner = owner;
        this.buyTime = LocalDateTime.now();
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
