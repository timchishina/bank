package org.example.Migrations;

import org.example.Banking.Facades.AccountFacade;
import org.example.Banking.Facades.CategoryFacade;
import org.example.Banking.Facades.OperationFacade;
import org.example.Banking.OperationData;
import org.example.Banking.BankAccount;
import org.example.Banking.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVImporter extends DataImporter {
    private final AccountFacade accountFacade;
    private final CategoryFacade categoryFacade;

    public CSVImporter(OperationFacade operationFacade, AccountFacade accountFacade, CategoryFacade categoryFacade) {
        super(operationFacade);
        this.accountFacade = accountFacade;
        this.categoryFacade = categoryFacade;
    }

    @Override
    protected List<OperationData> parseData(String rawData) {
        List<OperationData> operationsData = new ArrayList<>();
        String[] lines = rawData.split("\n");

        Map<Integer, Integer> accountIdMap = new HashMap<>();
        Map<Integer, Integer> categoryIdMap = new HashMap<>();

        int i = 0;
        while (i < lines.length && !lines[i].trim().isEmpty()) {
            String[] parts = lines[i].split(",");
            if (parts.length < 3) {
                i++;
                continue;
            }
            try {
                int oldId = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double balance = Double.parseDouble(parts[2].trim());

                BankAccount newAccount = accountFacade.create(oldId, name, balance);
                accountIdMap.put(oldId, newAccount.getId());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка парсинга банковского аккаунта: " + lines[i]);
            }
            i++;
        }

        i++;

        while (i < lines.length && !lines[i].trim().isEmpty()) {
            String[] parts = lines[i].split(",");
            if (parts.length < 3) {
                i++;
                continue;
            }
            try {
                int oldId = Integer.parseInt(parts[0].trim());
                boolean type = Boolean.parseBoolean(parts[1].trim());
                String name = parts[2].trim();

                Category newCategory = categoryFacade.create(oldId, type, name);
                categoryIdMap.put(oldId, newCategory.getId());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка парсинга категории: " + lines[i]);
            }
            i++;
        }
        i++;
        while (i < lines.length) {
            String line = lines[i].trim();
            String[] parts = line.split(",");
            if (parts.length < 5) {
                i++;
                continue;
            }

            try {
                boolean type = Boolean.parseBoolean(parts[0].trim());
                int oldBankAccountId = Integer.parseInt(parts[1].trim());
                double amount = Double.parseDouble(parts[2].trim());
                String description = parts[3].trim();
                int oldCategoryId = Integer.parseInt(parts[4].trim());

                Integer newBankAccountId = accountIdMap.get(oldBankAccountId);
                Integer newCategoryId = categoryIdMap.get(oldCategoryId);
                if (newBankAccountId == null) {
                    System.out.println("Ошибка: не найден банковский аккаунт с ID " + oldBankAccountId);
                    i++;
                    continue;
                }
                if (newCategoryId == null) {
                    System.out.println("Ошибка: не найдена категория с ID " + oldCategoryId);
                    i++;
                    continue;
                }

                operationsData.add(new OperationData(type, newBankAccountId, amount, description, newCategoryId));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка парсинга операции: " + line);
            }
            i++;
        }

        return operationsData;
    }
}
