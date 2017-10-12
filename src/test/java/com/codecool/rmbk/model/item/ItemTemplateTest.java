package com.codecool.rmbk.model.item;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ItemTemplateTest {

    private ItemTemplate itemTemplate;

    @BeforeEach
    void setup() {
        this.itemTemplate = new ItemTemplate("name", "description", "value", "special"));
    }

}