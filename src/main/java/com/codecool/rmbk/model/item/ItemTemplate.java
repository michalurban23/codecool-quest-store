package com.codecool.rmbk.model.item;

import static com.codecool.rmbk.model.item.ItemTemplate.CATEGORY.BASIC;
import static com.codecool.rmbk.model.item.ItemTemplate.CATEGORY.MAGIC;

public class ItemTemplate {

    private String name;
    private String value;
    private String description;
    private CATEGORY category;

    enum CATEGORY {
        BASIC,
        MAGIC;
    }

    public ItemTemplate(String name, String description, String value, String special) {

        this.name = name;
        this.value = value;
        this.description = description;
        if(special == "1") {
            this.category = MAGIC;
        } else if(special == "0") {
            this.category = BASIC;
        }
    }

    public String getValue() {

        return this.value;
    }

    public void setValue(String value) {
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
