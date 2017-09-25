package com.codecool.rmbk.model.item;

import java.util.ArrayList;
import java.util.UUID;

public class ItemTemplate {

    private UUID id;
    private Integer price;
    private String description;
    private static ArrayList<ItemTemplate> itemList = new ArrayList<>();

    static enum CATEGORY {
        BASIC,
        MAGIC;
    }

    public ItemTemplate(Integer price, String description) {

        this.price = price;
        this.description = description;
        this.id = UUID.randomUUID();
        itemList.add(this);
    }

    public Integer getPrice() {

        return this.price;
    }

    public void setPrice(Integer price) {

        this.price = price;
    }

    public String getDescription() {

        return this.description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
