package org.example.Commands.Categories;

import org.example.Commands.Command;
import org.example.Services.CategoryService;

public class ChangeCategoryCommand implements Command {
    private final CategoryService categoryService;
    private final boolean Type;
    private final String Name;
    private final int categoryId;
    public ChangeCategoryCommand(CategoryService categoryService, int categoryId, boolean type, String name) {
        this.categoryService = categoryService;
        this.Type = type;
        this.Name = name;
        this.categoryId = categoryId;
    }
    @Override
    public void execute() {
        if (!categoryService.categoryExists(categoryId)) {
            System.out.println("Ошибка: Категория не найдена.");
            return;
        }
        categoryService.editCategory(categoryId, Type, Name);
        System.out.println("Категория ID " + categoryId + " обновлена.");
    }
}