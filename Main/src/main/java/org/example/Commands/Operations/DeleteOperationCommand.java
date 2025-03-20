package org.example.Commands.Operations;

import org.example.Commands.Command;
import org.example.Services.OperationService;

public class DeleteOperationCommand implements Command {
    private final OperationService operationService;
    private final int operationId;

    public DeleteOperationCommand(OperationService operationService, int operationId) {
        this.operationService = operationService;
        this.operationId = operationId;
    }

    @Override
    public void execute() {
        if (!operationService.operationExists(operationId)) {
            System.out.println("Ошибка: Операция не найдена.");
            return;
        }
        operationService.deleteOperation(operationId);
        System.out.println("Операция ID " + operationId + " удалена.");
    }
}
