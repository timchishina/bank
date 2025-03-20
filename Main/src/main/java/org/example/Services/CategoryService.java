package org.example.Services;

import com.google.inject.Inject;
import org.example.Banking.Category;
import org.example.Banking.Facades.CategoryFacade;

public class CategoryService {
    private final CategoryFacade categoryFacade;

    @Inject
    public CategoryService(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
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
}
