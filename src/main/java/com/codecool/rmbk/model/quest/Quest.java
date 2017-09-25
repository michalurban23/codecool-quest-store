package com.codecool.rmbk.model.quest;

import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDateTime;
import com.codecool.rmbk.model.usr.Holder;
import com.codecool.rmbk.model.quest.QuestTemplate.CATEGORY;

public class Quest {

    private Holder owner;
    private LocalDateTime startTime;
    private UUID id;
    private CATEGORY category;
    private static ArrayList<Quest> questList = new ArrayList<>();

    public Quest(Holder owner) {

        this.id = UUID.randomUUID();
        this.owner = owner;
        this.startTime = LocalDateTime.now();
        this.category = CATEGORY.BASIC;
        questList.add(this);
    }

    public Holder getOwner() {

        return this.owner;
    }

    public void setOwner(Holder owner) {

        this.owner = owner;
    }

    public CATEGORY getCategory() {

        return this.category;
    }

    public void setCategory(CATEGORY category) {

        this.category = category;
    }
}
