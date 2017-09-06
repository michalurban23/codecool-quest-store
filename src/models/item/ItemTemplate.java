package models.item;

import java.util.ArrayList;

public class ItemTemplate {

    private Integer price;
    private String description;
    private ArrayList<Item> itemList;

    private enum CATEGORY {
        BASIC,
        MAGIC;
    }

    public ItemTemplate(Integer price, String description, ) {

        this.price = price;
        this.description = description;
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
