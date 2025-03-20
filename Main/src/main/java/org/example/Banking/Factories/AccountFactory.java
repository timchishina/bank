package org.example.Banking.Factories;

import org.example.Banking.Bank;
import org.example.Banking.BankAccount;

public class AccountFactory {
    static int CountAccount;
    AccountFactory () {
        CountAccount = 0;
    }
    public static BankAccount create(String Name, double Balance) {
        BankAccount account = new BankAccount();
        if (Balance > 0) {
            account.setBalance(Balance);
        } else {
            account.setBalance(0);
        }
        account.setName(Name);
        account.setId(CountAccount++);
        Bank.accounts.add(account);
        return account;
    }
    public static BankAccount create(int id, String Name, double Balance) {
        BankAccount account = new BankAccount();
        if (Balance > 0) {
            account.setBalance(Balance);
        } else {
            account.setBalance(0);
        }
        account.setName(Name);
        if (id > CountAccount) {
            CountAccount = id;
        }
        account.setId(CountAccount++);
        Bank.accounts.add(account);
        return account;
    }

}
