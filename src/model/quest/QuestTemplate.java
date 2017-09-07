package model.quest;

import java.util.ArrayList;
import java.util.LocalDateTime;
import java.util.UUID;
import model.usr.Holder;

public class QuestTemplate {

    private UUID id;
    private Integer value;
    private String description;
    private static ArrayList<QuestTemplate> questList = new ArrayList<>();

    static enum CATEGORY {
        BASIC,
        EXTRA;
    }

    public QuestTemplate(Integer value, String description) {

        this.value = value;
        this.description = description;
        this.id = UUID.randomUUID();
        questList.add(this);
    }

    public QuestTemplate(ArrayList<String> data) {

        Integer value = data.get(0);
        String description = data.get(0);
        String value = data.get(0);
        String value = data.get(0);

    }

}
