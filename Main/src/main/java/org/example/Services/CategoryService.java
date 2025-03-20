package org.example.Services;

import com.google.inject.Inject;
import org.example.Banking.Category;
import org.example.Banking.Facades.CategoryFacade;

import java.util.List;

public class CategoryService {
    private static CategoryFacade categoryFacade;

    @Inject
    public CategoryService(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    public static String getInfo(Category category) {
        return CategoryFacade.getInfo(category);
    }

    public static List<Category> getAllCategories() {
        return categoryFacade.getAllCategories();
    }

    public void addCategory(boolean Type, String name) {
        categoryFacade.create(Type, name);
    }

    public boolean categoryExists(int id) {
        return categoryFacade.find(id) != null;
    }

    public void editCategory(int id, boolean type, String newName) {
        categoryFacade.change(categoryFacade.find(id), type, newName);
    }

    public void deleteCategory(int id) {
        categoryFacade.delete(id);
    }

    public Category find(int categoryId) {
        return categoryFacade.find(categoryId);
    }

    public CategoryFacade getFacade() {
        return categoryFacade;
    }
}
