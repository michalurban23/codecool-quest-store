package com.codecool.rmbk.model.item;

import com.codecool.rmbk.model.usr.Holder;
import com.codecool.rmbk.model.item.ItemTemplate.CATEGORY;

public class MagicItem extends Item {

    private Double completion;

    public MagicItem(ItemTemplate template, int owner) {
        super(template, owner);
    }

    public Double getCompletion() {

        return this.completion;
    }
}
