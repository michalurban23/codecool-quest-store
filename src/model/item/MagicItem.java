package model.item;

import model.usr.Holder;
import model.item.ItemTemplate.CATEGORY;

public class MagicItem extends Item {

    private Double completion;

    public MagicItem(Holder owner) {
        super(owner);
        setCategory(CATEGORY.MAGIC);
    }

    public Double getCompletion() {

        return this.completion;
    }
}
