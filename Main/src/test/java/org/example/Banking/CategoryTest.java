package org.example.Banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getId() {
        Category c = new Category();
        c.setId(0);
        assertEquals(0, c.getId());
    }

    @Test
    void getName() {
        Category c = new Category();
        c.setName("a");
        assertEquals("a", c.getName());
    }

    @Test
    void getType() {
        Category c = new Category();
        c.setType(true);
        assertEquals(true, c.getType());
    }
}