package com.codecool.rmbk.model.item;

import java.time.LocalDate;

public class Item {

    private ItemTemplate template;
    private Integer owner;
    private LocalDate buyTime;
    private String completion;

    public Item(ItemTemplate template, Integer owner) {

        this.template = template;
        this.owner = owner;
        this.buyTime = LocalDate.now();
        if(template.getSpecial().equals("1")) {
            this.completion = "30";
        }
        else {
            this.completion = "NULL";
        }
    }

    public Integer getOwner() {

        return this.owner;
    }

    public void setOwner(Integer owner) {

        this.owner = owner;
    }

    public String getBuyTime() {

        return this.buyTime.toString();
    }

    public ItemTemplate getTemplate() {
        return template;
    }

    public String getCompletion() {
        return completion;
    }
}