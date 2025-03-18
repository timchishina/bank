package org.example.Banking;

import org.example.Bank;

public class CategoryFacade {
    public Category createCategory(boolean Type, String Name) {
        return CategoryFactory.createCategory(Type, Name);
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

    public static Category findCategory(int id) {
        for (Category tmp: Bank.categories) {
            if (id == tmp.getId()) {
                return tmp;
            }
        }
        return null;
    }

    public static void changeCategory(Category category, boolean Type) {
        if (category == null) {
            return;
        }
        category.setType(Type);
        CategoryFacade.changeCategories(category);
    }

    public static void changeCategory(Category category, String Name) {
        if (category == null) {
            return;
        }
        category.setName(Name);
        CategoryFacade.changeCategories(category);
    }

    public static void changeCategory(Category category, boolean Type, String Name) {
        if (category == null) {
            return;
        }
        CategoryFacade.changeCategory(category, Type);
        CategoryFacade.changeCategory(category, Name);
    }

    public static void deleteCategory(Category category) {
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

    public static void deleteCategory(int id) {
        Category category = findCategory(id);
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

}
