package com.codecool.rmbk.model.quest;

import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDateTime;
import com.codecool.rmbk.model.usr.Holder;

public class Quest {

    private Holder owner;
    private LocalDateTime startTime;
    private UUID id;

    public Quest(Holder owner) {

        this.id = UUID.randomUUID();
        this.owner = owner;
        this.startTime = LocalDateTime.now();
    }

    public Holder getOwner() {

        return this.owner;
    }

    public void setOwner(Holder owner) {

        this.owner = owner;
    }
}
