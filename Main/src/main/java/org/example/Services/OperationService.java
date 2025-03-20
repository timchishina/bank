package org.example.Services;

import com.google.inject.Inject;
import org.example.Banking.Operation;
import org.example.Banking.Facades.OperationFacade;

import java.util.List;

public class OperationService {
    private static OperationFacade operationFacade;

    @Inject
    public OperationService(OperationFacade operationFacade) {
        this.operationFacade = operationFacade;
    }

    public static void addOperation(boolean Type, int BankAccount, double Sum, String Description, int CategoryId) {
        operationFacade.create(Type, BankAccount, Sum, Description, CategoryId);
    }

    public static String getInfo(Operation operation) {
        return OperationFacade.getInfo(operation);
    }

    public boolean operationExists(int operationId) {
        return operationFacade.find(operationId) != null;
    }

    public void editOperation(Operation operation, boolean Type, int BankAccount, double Sum, String Description, int CategoryId) {
        operationFacade.change(operation, Type, BankAccount, Sum, Description, CategoryId);
    }

    public void deleteOperation(int operationId) {
        operationFacade.delete(operationFacade.find(operationId));
    }
    public Operation findOperation(int operationId) {
        return operationFacade.find(operationId);
    }

    public List<Operation> getAllOperations() {
        return operationFacade.getAllOperations();
    }
    public OperationFacade getFacade() {
        return operationFacade;
    }
}
