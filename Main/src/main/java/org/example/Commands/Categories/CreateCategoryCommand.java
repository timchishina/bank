package org.example.Commands.Categories;

import org.example.Commands.Command;
import org.example.Services.CategoryService;

public class CreateCategoryCommand implements Command {
    private final boolean Type;
    private final String Name;
    private final CategoryService categoryService;
    public CreateCategoryCommand(CategoryService categoryService, boolean type, String name) {
        this.categoryService = categoryService;
        this.Type = type;
        this.Name = name;
    }

    @Override
    public void execute() {
        if (Name == null || Name.trim().isEmpty()) {
            System.out.println("Ошибка: Название категории не может быть пустым.");
            return;
        }
        categoryService.addCategory(Type, Name);
        System.out.println("Добавлена категория: " + Name);
    }
}
