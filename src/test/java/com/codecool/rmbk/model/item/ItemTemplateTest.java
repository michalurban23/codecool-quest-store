package com.codecool.rmbk.model.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTemplateTest {

    private ItemTemplate itemTemplate;

    @BeforeEach
    void setup() {
        this.itemTemplate = new ItemTemplate("name", "description", "value", "special"));
    }

    @Test
    void testGetValue() {
        assertEquals("value", itemTemplate.getValue());
    }



}