package org.example.Commands.Operations;

import org.example.Banking.Operation;
import org.example.Commands.Command;
import org.example.Services.OperationService;

public class ChangeOperationCommand implements Command {
    private final OperationService operationService;
    private final Operation operation;
    private final boolean Type;
    private final int BankAccount;
    private final double Sum;
    private final String Description;
    private final int CategoryId;
    public ChangeOperationCommand(OperationService operationService, Operation operation, boolean Type, int BankAccount, double Sum, String Description, int CategoryId) {
        this.operationService = operationService;
        this.BankAccount = BankAccount;
        this.operation = operation;
        this.Type = Type;
        this.Sum = Sum;
        this.Description = Description;
        this.CategoryId = CategoryId;
    }
    @Override
    public void execute() {
        if (!operationService.operationExists(operation.getId())) {
            System.out.println("Ошибка: Операция не найдена.");
            return;
        }
        operationService.editOperation(operation, Type, BankAccount, Sum, Description, CategoryId);
        System.out.println("Операция ID " + operation.getId() + " обновлена.");
    }
}

