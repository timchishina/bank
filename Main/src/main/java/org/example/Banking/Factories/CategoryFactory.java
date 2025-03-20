package org.example.Banking.Factories;

import org.example.Banking.Bank;
import org.example.Banking.Category;

public class CategoryFactory {
    static int CountCategory;
    CategoryFactory() {
        CountCategory = 0;
    }
    public static Category createCategory(boolean Type, String Name) {
        Category category = new Category();
        category.setId(CountCategory++);
        category.setName(Name);
        category.setType(Type);
        Bank.categories.add(category);
        return category;
    }

    public static Category createCategory(int id, boolean Type, String Name) {
        Category category = new Category();
        if (id > CountCategory) {
            CountCategory = id;
        }
        category.setId(CountCategory++);
        category.setName(Name);
        category.setType(Type);
        Bank.categories.add(category);
        return category;
    }
}
