package org.example.Migrations;

import org.example.Banking.BankAccount;
import org.example.Banking.Category;
import org.example.Banking.Operation;

public class CSVExporter implements ExportVisitor {
    @Override
    public String visit(BankAccount account) {
        return account.getName() + "," + account.getBalance();
    }

    @Override
    public String visit(Operation operation) {
        return operation.getSum() + "," + operation.getDescription();
    }

    @Override
    public String visit(Category category) {
        return category.getName();
    }
}
