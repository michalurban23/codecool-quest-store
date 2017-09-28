package com.codecool.rmbk.model.item;

public class ItemTemplate {

    private String name;
    private String value;
    private String description;
    private String special;

    public ItemTemplate(String name, String description, String value, String special) {

        this.name = name;
        this.value = value;
        this.description = description;
        this.special = special;
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

    public String getSpecial() {
        return this.special;
    }
}
