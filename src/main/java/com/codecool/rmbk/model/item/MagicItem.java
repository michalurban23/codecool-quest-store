package com.codecool.rmbk.model.item;

import com.codecool.rmbk.model.usr.Holder;
import com.codecool.rmbk.model.item.ItemTemplate.CATEGORY;

public class MagicItem extends Item {

    private Double completion;

    public MagicItem(ItemTemplate template, String owner) {
        super(template);
    }

    public Double getCompletion() {

        return this.completion;
    }
}
