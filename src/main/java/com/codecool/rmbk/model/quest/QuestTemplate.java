package com.codecool.rmbk.model.quest;

import java.util.ArrayList;
import com.codecool.rmbk.model.usr.Holder;

public class QuestTemplate {

    private String name;
    private String description;
    private Integer value;
    private String special;
    private String active;

    public QuestTemplate(String[] data) {

        this.name = data[0];
        this.description = data[1];
        this.value = Integer.parseInt(data[2]);
        this.special = data[3];
        this.active = data[4];
    }

    public QuestTemplate(ArrayList<String> data) {

        this.name = data.get(0);
        this.description = data.get(1);
        this.value = Integer.parseInt(data.get(2));
        this.special = data.get(3);
        this.active = data.get(4);
    }

    public String getName() {

        return this.name;
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

    public String[] getQuestTemplate() {

        String[] data = {this.name, this.description, ""+this.value, this.special, this.active};
        return data;
    }

    public void updateData(String[] newData) {

        if (newData[0] != null) {
            this.description = newData[0];
        }
        if (newData[1] != null) {
            this.value = Integer.parseInt(newData[1]);
        }
        if (newData[2] != null) {
            this.special = newData[2];
        }
        if (newData[3] != null) {
            this.active = newData[3];
        }
    }
}
