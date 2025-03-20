package org.example.Migrations;

import org.example.Banking.BankAccount;
import org.example.Banking.Category;
import org.example.Banking.Operation;
import org.example.Services.BankService;
import org.example.Services.CategoryService;
import org.example.Services.OperationService;

public class CSVExporter implements ExportVisitor {
    @Override
    public String visit(BankAccount account) {
        return BankService.getInfo(account);
    }

    @Override
    public String visit(Operation operation) {
        return OperationService.getInfo(operation);
    }

    @Override
    public String visit(Category category) {
        return CategoryService.getInfo(category);
    }
}
