package org.example.Banking.Facades;

import org.example.Banking.Bank;
import org.example.Banking.Category;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryFacadeTest {

    @Test
    void find() {
        Bank Bank = new Bank();
        Category c = new Category();
        c.setId(0);
        Bank.categories.add(c);
        assertEquals(c, CategoryFacade.find(c.getId()));
    }

    @Test
    void change() {
        Bank Bank = new Bank();
        Category c = new Category();
        c.setId(0);
        Bank.categories.add(c);
        CategoryFacade.change(c, true, "s");
        c.setName("s");
        c.setType(true);
        assertEquals(c, CategoryFacade.find(0));
    }

    @Test
    void getInfo() {
        Category c = new Category();
        c.setId(0);
        c.setName("s");
        c.setType(true);
        String s = c.getId() + "," + c.getType() + "," + c.getName();
        assertEquals(s, CategoryFacade.getInfo(c));
    }

    @Test
    void getAllCategories() {
        Bank Bank = new Bank();
        Category c = new Category();
        c.setId(0);
        Bank.categories.add(c);
        List<Category> cat = new ArrayList<Category>();
        cat.add(c);
        assertEquals(cat, CategoryFacade.getAllCategories());
    }
}