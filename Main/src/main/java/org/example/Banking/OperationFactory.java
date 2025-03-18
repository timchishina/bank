package org.example.Banking;

import org.example.Bank;

import  java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationFactory {
    static int CountOperation;
    static double EPS = 0.0000000000000000000001;
    OperationFactory() {
        CountOperation = 0;
    }
    public static Operation createOperation(boolean Type, int BankAccount, double Sum, String Description, int CategoryId) {
        if (Sum < EPS) {
            return null;
        }
        Operation operation = new Operation();
        operation.setDate(LocalDateTime.now());
        operation.setId(CountOperation++);
        operation.setCategory_id(CategoryId);
        operation.setSum(Sum);
        operation.setBank_account_id(BankAccount);
        operation.setType(Type);

        operation.setDescription(Description);
        Bank.operations.computeIfAbsent(BankAccount, k -> new ArrayList<Operation>()).add(operation);
        return operation;
    }
}
