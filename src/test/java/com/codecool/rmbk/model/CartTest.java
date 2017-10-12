package com.codecool.rmbk.model;

import com.codecool.rmbk.model.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CartTest {

    Cart cart;

    @Mock
    Item item;

    @BeforeEach
    public void setup() {

        Cart cart = new Cart();
        this.cart = cart;

        Item item = mock(Item.class);
        this.item = item;
    }

    @Test
    public void testAddToCart() {

        cart.addToCart(item);

        assertEquals(1, cart.getItemsList().size());
    }

    @Test
    public void testRemoveCart() {

        cart.addToCart(item);
        cart.removeFromCart(item);

        assertEquals(0, cart.getItemsList().size());
    }

}