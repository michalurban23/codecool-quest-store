package com.codecool.rmbk.model.quest;

import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {

    ArrayList<String> template;
    User user;

    @BeforeEach
    public void setup() {

        ArrayList<String> template = new ArrayList<>();
        template.add("Explore a dungeon");
        template.add("Finishing a Teamwork week");
        template.add("100");
        this.template = template;

        User user = new Student("Agnieszka", "Koszany", "agi@codecool.com", "Kraków", 1);
        this.user = user;
    }

    @Test
    public void testParameterizedConstuctor() {

        Quest quest = new Quest(template, user);
        assertNotNull(quest.getOwnerID());
    }

    @Test
    public void testGetOwnerID() {

        Quest quest = new Quest(template, user);
        int questID = quest.getOwnerID();
        assertEquals(1, questID);
    }

    @Test
    public void testGetTemplateName() {

        Quest quest = new Quest(template, user);
        assertEquals("Explore a dungeon", quest.getTemplateName());
    }

    @Test
    public void testGetValue() {

        Quest quest = new Quest(template, user);
        assertEquals("100", quest.getValue());
    }
}