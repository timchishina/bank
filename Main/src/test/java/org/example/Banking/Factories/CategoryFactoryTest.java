package org.example.Banking.Factories;

import org.example.Banking.Category;
import org.junit.Test;
import org.example.Banking.Bank;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryFactoryTest {
    Bank b = new Bank();
    @BeforeEach
    void setUp() {
        Bank.categories.clear();
        CategoryFactory.CountCategory = 0;
    }

    @Test
    public void testCreateCategory() {
        setUp();
        Category category = CategoryFactory.createCategory(true, "Доход");
        assertEquals("Доход", category.getName());
        assertTrue(category.getType());
        assertEquals(0, category.getId());
        assertTrue(Bank.categories.contains(category));
    }

    @Test
    public void testCreateCategory_WithId() {
        setUp();
        Category category = CategoryFactory.createCategory(5, false, "Расход");

        assertEquals("Расход", category.getName());
        assertFalse(category.getType());
        assertEquals(5, category.getId());
        assertTrue(Bank.categories.contains(category));
    }

    @Test
    public void testCreateCategory_WithIdLowerThanCurrentCount() {
        setUp();
        CategoryFactory.createCategory(true, "Доход 1");
        CategoryFactory.createCategory(true, "Доход 2");

        Category category = CategoryFactory.createCategory(3, false, "Расход");

        assertEquals(3, category.getId());
    }
}
