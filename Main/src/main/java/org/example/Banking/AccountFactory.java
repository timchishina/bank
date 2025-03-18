package org.example.Banking;

import org.example.Bank;

public class AccountFactory {
    static int CountAccount;
    AccountFactory () {
        CountAccount = 0;
    }
    public static BankAccount createAccount(String Name, double Balance) {
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

}
