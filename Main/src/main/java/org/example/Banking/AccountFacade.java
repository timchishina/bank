package org.example.Banking;

import org.example.Bank;

public class AccountFacade {
    public BankAccount createAccount(String Name, double Balance) {
        return AccountFactory.createAccount(Name, Balance);
    }

    public BankAccount findAccount(int id) {
        for (BankAccount bankAccount: Bank.accounts) {
            if (id == bankAccount.getId()) {
                return bankAccount;
            }
        }
        return null;
    }

    private static void changeAccounts(BankAccount bankAccount) {
        for (BankAccount tmp: Bank.accounts) {
            if (bankAccount.getId() == tmp.getId()) {
                tmp = bankAccount;
                return;
            }
        }
    }

    public void changeAccount(BankAccount bankAccount, String Name, double Balance) {
        if (bankAccount == null) {
            return;
        }
        bankAccount.setName(Name);
        bankAccount.setBalance(Balance);
        AccountFacade.changeAccounts(bankAccount);
    }

    public void deleteAccount(BankAccount bankAccount) {
        if (bankAccount == null) {
            return;
        }

        for (BankAccount tmp: Bank.accounts) {
            if (bankAccount.getId() == tmp.getId()) {
                Bank.accounts.remove(tmp);
                return;
            }
        }
    }
}
