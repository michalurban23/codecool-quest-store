package com.codecool.rmbk.model.quest;

import com.codecool.rmbk.model.usr.Holder;
import com.codecool.rmbk.model.quest.QuestTemplate.CATEGORY;

public class ExtraQuest extends Quest {

    public ExtraQuest(Holder owner) {
        super(owner);
        setCategory(CATEGORY.EXTRA);
    }
}
