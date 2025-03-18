package org.example.Banking;

import org.example.Bank;

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
}
