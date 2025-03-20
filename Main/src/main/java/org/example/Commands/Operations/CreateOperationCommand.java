package org.example.Commands.Operations;

import org.example.Banking.Facades.OperationFacade;
import org.example.Commands.Command;
import org.example.Services.OperationService;

public class CreateOperationCommand implements Command {
    private final OperationService operationService;
    private final double Sum;
    private final String description;
    private final boolean Type;
    private final int BankAccount;
    private final int CategoryId;
    public CreateOperationCommand(OperationService operationService, boolean Type, int BankAccount, double sum, String Description, int CategoryId) {
        this.operationService = operationService;
        this.Sum = sum;
        this.description = Description;
        this.CategoryId = CategoryId;
        this.Type = Type;
        this.BankAccount = BankAccount;
    }

    @Override
    public void execute() {
        OperationFacade.create(Type, BankAccount, Sum, description, CategoryId);
        System.out.println("Добавлена операция: " + Sum + " (" + description + ")");
    }
}