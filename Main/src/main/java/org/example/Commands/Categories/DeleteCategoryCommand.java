package org.example.Commands.Categories;

import org.example.Commands.Command;
import org.example.Services.CategoryService;

public class DeleteCategoryCommand implements Command {
    private final CategoryService categoryService;
    private final int categoryId;
    public DeleteCategoryCommand(CategoryService categoryService, int category){
        this.categoryService = categoryService;
        this.categoryId = category;
    }
    @Override
    public void execute() {
        if (!categoryService.categoryExists(categoryId)) {
            System.out.println("Ошибка: Категория не найдена.");
            return;
        }
        categoryService.deleteCategory(categoryId);
        System.out.println("Категория ID " + categoryId + " удалена.");
    }
}