package org.example.Banking.Facades;

import org.example.Banking.Bank;
import org.example.Banking.Operation;
import org.example.Banking.Factories.OperationFactory;

import java.util.ArrayList;
import java.util.List;

public class OperationFacade {
    public static Operation create(boolean Type, int BankAccount, double Sum, String Description, int CategoryId) {
        return OperationFactory.create(Type, BankAccount, Sum, Description, CategoryId);
    }
    
    private static void changeOperations(Operation operation) {
        if (operation == null) {
            return;
        }
        for (Operation tmp: Bank.operations.get(operation.getBank_account_id())) {
            if (operation.getId() == tmp.getId()) {
                tmp = operation;
            }
        }
    }

    public static List<Operation> view(int accountId) {
        if (AccountFacade.find(accountId) == null) {
            return null;
        }
        return Bank.operations.get(accountId);
    }

    public static Operation find(int id) {
        for (Operation tmp: Bank.operations.get(id)) {
            if (id == tmp.getId()) {
                return tmp;
            }
        }
        return null;
    }

    public static void change(Operation operation, boolean Type, int BankAccount, double Sum, String Description, int CategoryId) {
        if (operation == null) {
            return;
        }
        operation.setDescription(Description);
        operation.setType(Type);
        operation.setSum(Sum);
        operation.setBank_account_id(BankAccount);
        operation.setCategory_id(CategoryId);
        OperationFacade.changeOperations(operation);
    }

    public static void delete(Operation operation) {
        for (Operation tmp: Bank.operations.get(operation.getBank_account_id())) {
            if (operation.getId() == tmp.getId()) {
                Bank.operations.get(operation.getBank_account_id()).remove(tmp);
                return;
            }
        }
    }

    public static String getInfo(Operation operation) {
        return operation.getType() + "," + operation.getBank_account_id() + "," + operation.getSum() + "," + operation.getDescription() + "," + operation.getCategory_id();
    }

    public static List<Operation> getAllOperations() {
        List<Operation> allOperations = new ArrayList<>();
        for (List<Operation> ops : Bank.operations.values()) {
            allOperations.addAll(ops);
        }
        return allOperations;
    }
}
