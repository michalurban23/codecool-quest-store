package com.codecool.rmbk.model.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ItemTest {

    @Test
    void testCreateItem() {
        ItemTemplate itemTemplate = mock(ItemTemplate.class);
        when(itemTemplate.getSpecial()).thenReturn("1");
        Item item = new Item(itemTemplate, 1);
        assertNotNull(item);
    }

    @Test
    void testGetOwner() {
        ItemTemplate itemTemplate = mock(ItemTemplate.class);
        when(itemTemplate.getSpecial()).thenReturn("1");
        Item item = new Item(itemTemplate, 1);
        assertEquals(1, (int) item.getOwner());
    }

    @Test
    void testGetTemplate() {
        ItemTemplate itemTemplate = mock(ItemTemplate.class);
        when(itemTemplate.getSpecial()).thenReturn("1");
        Item item = new Item(itemTemplate, 1);
        assertNotNull(item.getTemplate());
    }

    @Test
    void testGetBuyTime() {
        ItemTemplate itemTemplate = mock(ItemTemplate.class);
        when(itemTemplate.getSpecial()).thenReturn("1");
        Item item = new Item(itemTemplate, 1);
        assertNotNull(item.getBuyTime());
    }

}