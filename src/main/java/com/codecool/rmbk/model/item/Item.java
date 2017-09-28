package com.codecool.rmbk.model.item;

import java.time.LocalDateTime;
import com.codecool.rmbk.model.usr.Holder;
import com.codecool.rmbk.model.item.ItemTemplate.CATEGORY;

public class Item {

    private ItemTemplate template;
    private int owner;
    private int id;
    private LocalDateTime buyTime;

    public Item(ItemTemplate template, int owner) {

        this.id = id;
        this.template = template;
        this.owner = owner;
        this.buyTime = LocalDateTime.now();
    }

    public int getOwner() {

        return this.owner;
    }

    public void setOwner(int owner) {

        this.owner = owner;
    }

    public LocalDateTime getBuyTime() {

        return this.buyTime;
    }

    public void setBuyTime(LocalDateTime buyTime) {

        this.buyTime = buyTime;
    }

}
