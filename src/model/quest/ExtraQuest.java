package model.quest;

import model.usr.Holder;
import model.quest.QuestTemplate.CATEGORY;

public class ExtraQuest extends Quest {

    public ExtraQuest(Holder owner) {
        super(owner);
        setCategory(CATEGORY.EXTRA);
    }
}
