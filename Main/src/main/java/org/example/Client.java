package org.example;

import org.example.Banking.BankAccount;
import org.example.Banking.Category;
import org.example.Banking.Facades.AccountFacade;
import org.example.Banking.Facades.CategoryFacade;
import org.example.Banking.Facades.OperationFacade;
import org.example.Banking.Operation;
import org.example.Commands.Banks.ChangeBankAccountCommand;
import org.example.Commands.Banks.CreateBankAccountCommand;
import org.example.Commands.Banks.DeleteBankAccountCommand;
import org.example.Commands.Categories.ChangeCategoryCommand;
import org.example.Commands.Categories.CreateCategoryCommand;
import org.example.Commands.Categories.DeleteCategoryCommand;
import org.example.Commands.Operations.ChangeOperationCommand;
import org.example.Commands.Operations.CreateOperationCommand;
import org.example.Commands.Operations.DeleteOperationCommand;
import org.example.Migrations.*;
import org.example.Services.BankService;
import org.example.Services.CategoryService;
import org.example.Services.OperationService;
import org.example.Commands.*;
import org.junit.experimental.categories.Categories;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Client {
    private final BankService bankService;
    private final OperationService operationService;
    private final CategoryService categoryService;
    private final Scanner scanner;

    public Client(BankService bankService, OperationService operationService, CategoryService categoryService) {
        this.bankService = bankService;
        this.operationService = operationService;
        this.categoryService = categoryService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("\n=== Главное меню ===");
                System.out.println("1 - Управление банковскими счетами");
                System.out.println("2 - Управление операциями");
                System.out.println("3 - Управление категориями");
                System.out.println("4 - Импорт данных");
                System.out.println("5 - Экспорт данных");
                System.out.println("6 - Выход");

                int choice = getIntInput("Выберите действие: ");
                switch (choice) {
                    case 1 -> manageBankAccounts();
                    case 2 -> manageOperations();
                    case 3 -> manageCategories();
                    case 4 -> importData();
                    case 5 -> exportData();
                    case 6 -> {
                        System.out.println("Выход...");
                        return;
                    }
                    default -> System.out.println("Ошибка: Некорректный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void importData() {
        System.out.println("\n=== Импорт данных ===");
        System.out.println("Формат файла - CSV");
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.next();
        DataImporter importer = new CSVImporter(operationService.getFacade(), BankService.getFacade(), categoryService.getFacade());
        if (importer != null) {
            importer.importData(filePath);
        }
    }

    private void exportData() {
        System.out.println("\n=== Экспорт данных ===");
        System.out.println("Формат - CSV");
        System.out.print("Введите путь для сохранения файла: ");
        String filePath = scanner.next();
        ExportVisitor exporter = new CSVExporter();
        if (exporter != null) {
            List<Operation> operations = operationService.getAllOperations();
            List<BankAccount> accounts = BankService.getAllAccounts();
            List<Category> categories = CategoryService.getAllCategories();
            StringBuilder exportedData = new StringBuilder();
            for (BankAccount bk : accounts) {
                exportedData.append(bk.accept(exporter)).append("\n");
            }
            exportedData.append("\n");
            for (Category c : categories) {
                exportedData.append(c.accept(exporter)).append("\n");
            }
            exportedData.append("\n");
            for (Operation op : operations) {
                exportedData.append(op.accept(exporter)).append("\n");
            }
            FileUtil.writeToFile(filePath, exportedData.toString());
        }
    }

    private void manageBankAccounts() {
        while (true) {
            System.out.println("\n=== Управление банковскими счетами ===");
            System.out.println("1 - Добавить счет");
            System.out.println("2 - Редактировать счет");
            System.out.println("3 - Удалить счет");
            System.out.println("4 - Посмотреть все счета");
            System.out.println("5 - Назад");

            int choice = getIntInput("Выберите действие: ");
            switch (choice) {
                case 1 -> addBankAccount();
                case 2 -> editBankAccount();
                case 3 -> deleteBankAccount();
                case 4 -> viewBankAccounts();
                case 5 -> { return; }
                default -> System.out.println("Ошибка: Некорректный выбор.");
            }
        }
    }

    private void viewBankAccounts() {
        System.out.println("Вот все счета: ");
        List<BankAccount> bankAccounts = AccountFacade.getAllAccounts();
        if (bankAccounts == null) {
            System.out.println("Счеты не найдены");
            return;
        }
        for (BankAccount bk: bankAccounts) {
            System.out.println(bk.getName() + " аккаунт с id = " + bk.getId() + " и балансом " + bk.getBalance());
        }
    }

    private void viewCategories() {
        System.out.println("Вот все категории: ");
        List<Category> categories = CategoryFacade.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("Категории не найдены");
            return;
        }
        for (Category ct: categories) {
            System.out.println(ct.getName() + " категория с id = " + ct.getId() + " и типом " + (ct.getType()? "доход":"расход"));
        }
    }

    private void addBankAccount() {
        scanner.nextLine();
        System.out.print("Введите название счета: ");
        String name = scanner.nextLine();
        double balance = getDoubleInput("Введите начальный баланс: ");
        int time = getIntInput("Если вы хотите измерить время работы команды введите 1, или любую другую цифру если нет:");
        Command command;
        switch(time) {
            case 1:
                command = new TimedCommand(new CreateBankAccountCommand(bankService, name, balance));
                break;
            default:
                command = new CreateBankAccountCommand(bankService, name, balance);
        }
        command.execute();
    }

    private void editBankAccount() {
        int accountId = getIntInput("Введите ID счета для редактирования: ");
        if (!bankService.accountExists(accountId)) {
            System.out.println("Ошибка: Счет не найден.");
            return;
        }

        scanner.nextLine();
        System.out.print("Введите новое название счета: ");
        String newName = scanner.nextLine();
        double newBalance = getDoubleInput("Введите новый баланс: ");
        int time = getIntInput("Если вы хотите измерить время работы команды введите 1, или любую другую цифру если нет:");
        Command command;
        switch(time) {
            case 1:
                command = new TimedCommand(new ChangeBankAccountCommand(bankService, accountId, newName, newBalance));
            default:
                command = new ChangeBankAccountCommand(bankService, accountId, newName, newBalance);
        }
        command.execute();
    }

    private void deleteBankAccount() {
        int accountId = getIntInput("Введите ID счета для удаления: ");
        if (!bankService.accountExists(accountId)) {
            System.out.println("Ошибка: Счет не найден.");
            return;
        }

        Command command = new DeleteBankAccountCommand(bankService, accountId);
        command.execute();
    }

    private void viewOperations() {
        int accountId = getIntInput("Введите ID счета: ");
        if (AccountFacade.find(accountId) == null) {
            System.out.println("Такого счета не существует");
            return;
        }
        System.out.println("Вот все операции по счету: ");
        List<Operation> operations = OperationFacade.view(accountId);
        if (operations == null) {
            System.out.println("На этом счете нет операций");
            return;
        }
        for (Operation op: operations) {
            System.out.println(CategoryFacade.find(op.getCategory_id()).getName() + (op.getType()?" доход ":" расход ") + "c балансом " + op.getSum() + " операция с id = " + op.getId() + " ");
        }

    }
    private void manageOperations() {
        while (true) {
            System.out.println("\n=== Управление операциями ===");
            System.out.println("1 - Добавить операцию");
            System.out.println("2 - Редактировать операцию");
            System.out.println("3 - Удалить операцию");
            System.out.println("4 - Посмотреть все операции");
            System.out.println("5 - Назад");

            int choice = getIntInput("Выберите действие: ");
            switch (choice) {
                case 1 -> addOperation();
                case 2 -> editOperation();
                case 3 -> deleteOperation();
                case 4 -> viewOperations();
                case 5 -> { return; }
                default -> System.out.println("Ошибка: Некорректный выбор.");
            }
        }
    }

    private void addOperation() {
        int accountId = getIntInput("Введите ID счета: ");
        if (!bankService.accountExists(accountId)) {
            System.out.println("Ошибка: Счет не найден.");
            return;
        }

        double amount = getDoubleInput("Введите сумму операции: ");
        scanner.nextLine();
        System.out.print("Введите описание операции: ");
        String description = scanner.nextLine();
        int categoryId = getIntInput("Введите id категории операции: ");
        if (!categoryService.categoryExists(categoryId)) {
            System.out.println("Ошибка: Категория не найдена.");
            return;
        }
        Command command = new CreateOperationCommand(operationService, categoryService.find(categoryId).getType(),  accountId, amount, description, categoryId);
        command.execute();
    }

    private void editOperation() {
        int operationId = getIntInput("Введите ID операции для редактирования: ");
        if (!operationService.operationExists(operationId)) {
            System.out.println("Ошибка: Операция не найдена.");
            return;
        }

        double newAmount = getDoubleInput("Введите новую сумму: ");
        scanner.nextLine();
        System.out.print("Введите новое описание: ");
        String newDescription = scanner.nextLine();
        Command command = new ChangeOperationCommand(operationService, operationService.findOperation(operationId),
                operationService.findOperation(operationId).getType(),
                operationService.findOperation(operationId).getBank_account_id(), newAmount, newDescription,
                operationService.findOperation(operationId).getCategory_id());
        command.execute();
    }

    private void deleteOperation() {
        int operationId = getIntInput("Введите ID операции для удаления: ");
        if (!operationService.operationExists(operationId)) {
            System.out.println("Ошибка: Операция не найдена.");
            return;
        }

        Command command = new DeleteOperationCommand(operationService, operationId);
        command.execute();
    }

    private void manageCategories() {
        while (true) {
            System.out.println("\n=== Управление категориями ===");
            System.out.println("1 - Добавить категорию");
            System.out.println("2 - Редактировать категорию");
            System.out.println("3 - Удалить категорию");
            System.out.println("4 - Посмотреть все категории");
            System.out.println("5 - Назад");

            int choice = getIntInput("Выберите действие: ");
            switch (choice) {
                case 1 -> addCategory();
                case 2 -> editCategory();
                case 3 -> deleteCategory();
                case 4 -> viewCategories();
                case 5 -> { return; }
                default -> System.out.println("Ошибка: Некорректный выбор.");
            }
        }
    }

    private void addCategory() {
        scanner.nextLine();
        System.out.print("Введите название категории: ");
        String name = scanner.nextLine();
        boolean type = false;
        while (true) {
            int Type = getIntInput("Введите тип категории: 1 - доход, или 2 - расход: ");
            if (Type == 1) {
                type = true;
                break;
            } else if (Type == 2) {
                type = false;
                break;
            }
        }
        Command command = new CreateCategoryCommand(categoryService, type, name);
        command.execute();
    }

    private void editCategory() {
        int categoryId = getIntInput("Введите ID категории: ");
        if (!categoryService.categoryExists(categoryId)) {
            System.out.println("Ошибка: Категория не найдена.");
            return;
        }

        scanner.nextLine();
        System.out.print("Введите новое название категории: ");
        String newName = scanner.nextLine();

        boolean type = false;
        Command command = new ChangeCategoryCommand(categoryService, categoryId, type, newName);
        command.execute();
    }

    private void deleteCategory() {
        int categoryId = getIntInput("Введите ID категории: ");
        if (!categoryService.categoryExists(categoryId)) {
            System.out.println("Ошибка: Категория не найдена.");
            return;
        }

        Command command = new DeleteCategoryCommand(categoryService, categoryId);
        command.execute();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите число.");
                scanner.nextLine();
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите число.");
                scanner.nextLine();
            }
        }
    }
}
