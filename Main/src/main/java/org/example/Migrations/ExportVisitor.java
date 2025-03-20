package org.example.Migrations;

import org.example.Banking.*;

public interface ExportVisitor {
    String visit(BankAccount account);
    String visit(Operation operation);
    String visit(Category category);
}
