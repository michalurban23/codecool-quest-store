package com.codecool.rmbk.model.usr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static User user;
    @BeforeAll
    public static void userConstructor(){
        if(user == null){
            user = new Student("Jan", "Kowalski", "jan.kowalski@o2.pl", "Kraków", 15);
        }
    }

    @Test
    public void testIfConstructorCreateObject(){
        assertNotNull(user);
    }
    @Test
    public void testIfConstructorMakesPropperFirstNameValue(){
        assertEquals("Jan", user.getFirstName());
    }

    @Test
    public void testIfConstructorMakesPropperLastNameValue(){
        assertEquals("Kowalski", user.getLastName());
    }

    @Test
    public void testIfConstructorMakesPropperEmailValue(){
        assertEquals("jan.kowalski@o2.pl", user.getEmail());
    }

    @Test
    public void testIfConstructorMakesPropperAdressValue(){
        assertEquals("Kraków", user.getAddress());
    }

    @Test
    public void testIfConstructorMakesPropperIdValue(){
        assertEquals(15, user.getID());
    }
    @Test
    public void testIfFirstNameSetterSetsPropperValue(){
        user.setFirstName("Maria");
        assertEquals("Maria", user.getFirstName());
    }

    @Test
    public void testIfLastNameSetterSetsPropperValue(){
        user.setLastName("Nowak");
        assertEquals("Nowak", user.getLastName());
    }

    @Test
    public void testIfEmailSetterSetsPropperValue(){
        user.setEmail("maria.nowak@o2.pl");
        assertEquals("maria.nowak@o2.pl", user.getEmail());
    }

    @Test
    public void testIfEmailSetterThrowsExceptionWhenMialhasInappriopirateFormat() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> user.setEmail("maria.nowak")),
                () -> assertThrows(IllegalArgumentException.class, () -> user.setEmail("maria")),
                () -> assertThrows(IllegalArgumentException.class, () -> user.setEmail("@ow.pl")),
                () -> assertThrows(IllegalArgumentException.class, () -> user.setEmail("maria@pl")),
                () -> assertThrows(IllegalArgumentException.class, () -> user.setEmail(""))
        );
    }
}