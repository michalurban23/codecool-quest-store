package com.codecool.rmbk.model.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemTest {

    private Item item;

    @BeforeEach
    void setup() {
        ItemTemplate itemTemplate = mock(ItemTemplate.class);
        Item item = new Item(itemTemplate, 1);
    }

    @Test
    void testCreateItem() {
        assertNotNull(item);
    }





}