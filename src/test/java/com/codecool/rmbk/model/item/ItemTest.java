package com.codecool.rmbk.model.item;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

    private ItemTemplate itemTemplate;
    private Item item;

    @BeforeEach
    void setup() {
        this.itemTemplate = new ItemTemplate("test", "test",
                "test", "1");
        this.item = new Item(itemTemplate, 1);
    }

    @Test
    void testCreateItem() {
        assertEquals(this.itemTemplate, item.getTemplate());
        assertEquals(1, (int) item.getOwner());
    }

    @Test
    void testGetSetOwner() {
        item.setOwner(2);
        assertEquals(2, (int) item.getOwner());
    }

    @Test
    void testGetTemplate() {
        assertEquals(itemTemplate, item.getTemplate());
    }

    @Test
    void testGetBuyTime() {
        assertNotNull(item.getBuyTime());
    }

    @Test
    void testGetCompletion() {
        assertEquals("30", item.getCompletion());
    }

    @Test
    void testSetRightCompletion() {
        ItemTemplate itemTemplate2 = new ItemTemplate("test", "test",
                "test", "0");
        Item item2 = new Item(itemTemplate2, 1);
        assertEquals("NULL", item2.getCompletion());
    }

    @Test
    void testSetRightOwner() {
        assertThrows(IllegalArgumentException.class, () -> new Item(itemTemplate, -1));
    }
}