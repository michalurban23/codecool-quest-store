package com.codecool.rmbk.model.item;

public class MagicItem extends Item {

    private String completion;

    public MagicItem(ItemTemplate template, Integer owner) {
        super(template, owner);
    }

    public String getCompletion() {

        return this.completion;
    }
}
