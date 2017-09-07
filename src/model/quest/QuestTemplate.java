package model.quest;

import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDateTime;
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

        Integer value = Integer.parseInt(data.get(0));
        String description = data.get(1);
        UUID id = UUID.fromString(data.get(2));

        this.value = value;
        this.description = description;
        this.id = id;
        questList.add(this);
    }

    public Integer getValue() {

        return this.value;
    }

    public void setValue(Integer value) {

        this.value = value;
    }

    public String getDescription() {

        return this.description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
