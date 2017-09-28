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

    public CATEGORY getCategory() {

        return this.category;
    }

    public void setCategory(CATEGORY category) {

        this.category = category;
    }
}
