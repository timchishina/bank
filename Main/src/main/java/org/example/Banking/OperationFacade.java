package org.example.Banking;

import org.example.Bank;

public class OperationFacade {
    public Operation createOperation(boolean Type, int BankAccount, double Sum, String Description, int CategoryId) {
        return OperationFactory.createOperation(Type, BankAccount, Sum, Description, CategoryId);
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

    public static Operation findOperation(int id) {
        for (Operation tmp: Bank.operations.get(id)) {
            if (id == tmp.getId()) {
                return tmp;
            }
        }
        return null;
    }

    public static void changeOperation(Operation operation, boolean Type, int BankAccount, double Sum, String Description, int CategoryId) {
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

    public static void deleteOperation(Operation operation) {
        for (Operation tmp: Bank.operations.get(operation.getBank_account_id())) {
            if (operation.getId() == tmp.getId()) {
                Bank.operations.get(operation.getBank_account_id()).remove(tmp);
                return;
            }
        }
    }
}
