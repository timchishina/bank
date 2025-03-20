package org.example.Migrations;

import org.example.Banking.BankAccount;
import org.example.Banking.Category;
import org.example.Banking.Operation;

public class JSONExporter implements ExportVisitor {
    @Override
    public String visit(BankAccount account) {
        return "{ \"name\": \"" + account.getName() + "\", \"balance\": " + account.getBalance() + " }";
    }

    @Override
    public String visit(Operation operation) {
        return "{ \"amount\": " + operation.getSum() + ", \"description\": \"" + operation.getDescription() + "\" }";
    }

    @Override
    public String visit(Category category) {
        return "{ \"name\": \"" + category.getName() + "\" }";
    }
}
