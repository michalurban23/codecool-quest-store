package com.codecool.rmbk.model.item;

import java.time.LocalDateTime;

public class Item {

    private ItemTemplate template;
    private String owner;
    private Integer id;
    private LocalDateTime buyTime;

    public Item(ItemTemplate template, Integer id) {

        this.id = id;
        this.template = template;
        this.owner = owner;
        this.buyTime = LocalDateTime.now();
    }

    public String getOwner() {

        return this.owner;
    }

    public void setOwner(String owner) {

        this.owner = owner;
    }

    public LocalDateTime getBuyTime() {

        return this.buyTime;
    }

    public void setBuyTime(LocalDateTime buyTime) {

        this.buyTime = buyTime;
    }

    public ItemTemplate getTemplate() {
        return template;
    }
}
