package org.example.Banking.Facades;

import org.example.Banking.Factories.AccountFactory;
import org.example.Banking.Bank;
import org.example.Banking.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountFacade {
    public static List<BankAccount> getAllAccounts() {
        return Bank.accounts;
    }


    public static BankAccount create(String Name, double Balance) {
        return AccountFactory.create(Name, Balance);
    }

    public static BankAccount create(int id, String Name, double Balance) {
        return AccountFactory.create(id, Name, Balance);
    }

    public static BankAccount find(int id) {
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

    public  static void change(int bankAccount, String Name, double Balance) {
        if (find(bankAccount) == null) {
            return;
        }

        find(bankAccount).setName(Name);
        find(bankAccount).setBalance(Balance);
        AccountFacade.changeAccounts(find(bankAccount));
    }

    public static void delete(int bankAccount) {
        if (find(bankAccount) == null) {
            return;
        }

        for (BankAccount tmp: Bank.accounts) {
            if (bankAccount == tmp.getId()) {
                Bank.accounts.remove(tmp);
                return;
            }
        }
    }

    public static String getInfo(BankAccount account) {
        return account.getId() + "," + account.getName() + "," + account.getBalance();
    }
}
