package com.codecool.rmbk.model.item;

import com.codecool.rmbk.model.usr.User;

import java.time.LocalDate;
import java.util.List;

public class Item {

    private ItemTemplate template;
    private String templateName;
    private Integer owner;
    private LocalDate buyTime;
    private String completion;
    private String originalValue;

    public Item(ItemTemplate template, Integer owner) {

        this.template = template;
        this.owner = owner;
        this.buyTime = LocalDate.now();
        this.completion = "NULL";
    }

    public Item(List<String> data, User user) {

        this.templateName = data.get(0);
        this.owner = user.getID();
        this.buyTime = LocalDate.now();
        this.originalValue = data.get(1);
        this.completion = "NULL";
    }

    public Item(List<String> data, String ownerId) {

        this.templateName = data.get(0);
        this.owner = Integer.parseInt(ownerId);
        this.buyTime = LocalDate.now();
        this.originalValue = data.get(1);
        this.completion = "NULL";
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

    public String getTemplateName() {

        return templateName;
    }

    public void setTemplateName(String templateName) {

        this.templateName = templateName;
    }

    public String getOriginalValue() {

        return originalValue;
    }
}