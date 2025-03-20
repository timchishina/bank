package org.example.Banking.Factories;

import org.example.Banking.Bank;
import org.example.Banking.Facades.AccountFacade;
import org.example.Banking.Operation;

import  java.time.LocalDateTime;
import java.util.ArrayList;

public class OperationFactory {
    static int CountOperation;
    private static double EPS = 0.0000000000000000000001;
    OperationFactory() {
        CountOperation = 0;
    }
    public static Operation create(boolean Type, int BankAccount, double Sum, String Description, int CategoryId) {
        if (Sum < EPS) {
            return null;
        }
        if (!Type) {
            if (AccountFacade.find(BankAccount).getBalance() - Sum <= 0) {
                return null;
            } else {
                AccountFacade.change(BankAccount, AccountFacade.find(BankAccount).getName(),
                        AccountFacade.find(BankAccount).getBalance() - Sum);
            }
        } else {
            AccountFacade.change(BankAccount, AccountFacade.find(BankAccount).getName(),
                    AccountFacade.find(BankAccount).getBalance() + Sum);
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
