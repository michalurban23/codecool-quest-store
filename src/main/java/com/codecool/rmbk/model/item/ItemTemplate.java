package com.codecool.rmbk.model.item;

import java.util.ArrayList;
import java.util.UUID;

public class ItemTemplate {

    private String name;
    private Integer value;
    private String description;
    private CATEGORY category;

    enum CATEGORY {
        BASIC,
        MAGIC;
    }

    public ItemTemplate(String name, String description, int value, CATEGORY category) {

        this.name = name;
        this.value = value;
        this.description = description;
        this.category = category;
    }

    public Integer getValue() {

        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


    public String getDescription() {

        return this.description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }
}
