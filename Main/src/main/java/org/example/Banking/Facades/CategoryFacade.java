package org.example.Banking.Facades;

import org.example.Banking.Bank;
import org.example.Banking.Category;
import org.example.Banking.Factories.CategoryFactory;

import java.util.List;

public class CategoryFacade{
    public static Category create(boolean Type, String Name) {
        return CategoryFactory.createCategory(Type, Name);
    }

    public static Category create(int id, boolean Type, String Name) {
        return CategoryFactory.createCategory(id, Type, Name);
    }

    private static void changeCategories(Category category) {
        if (category == null) {
            return;
        }
        for (Category tmp: Bank.categories) {
            if (category.getId() == tmp.getId()) {
                tmp = category;
            }
        }
    }

    public static Category find(int id) {
        for (Category tmp: Bank.categories) {
            if (id == tmp.getId()) {
                return tmp;
            }
        }
        return null;
    }

    private static void changeCategory(Category category, boolean Type) {
        if (category == null) {
            return;
        }
        category.setType(Type);
        CategoryFacade.changeCategories(category);
    }

    private static void changeCategory(Category category, String Name) {
        if (category == null) {
            return;
        }
        category.setName(Name);
        CategoryFacade.changeCategories(category);
    }
    public static void change(Category category, boolean Type, String Name) {
        if (category == null) {
            return;
        }
        CategoryFacade.changeCategory(category, Type);
        CategoryFacade.changeCategory(category, Name);
    }

    public static void delete(int id) {
        Category category = find(id);
        if (category == null) {
            return;
        }
        for (Category tmp: Bank.categories) {
            if (category.getId() == tmp.getId()) {
                Bank.categories.remove(tmp);
                return;
            }
        }
    }

    public static String getInfo(Category category) {
        return category.getId() + "," + category.getType() + "," + category.getName();
    }

    public static List<Category> getAllCategories() {
        return Bank.categories;
    }
}
