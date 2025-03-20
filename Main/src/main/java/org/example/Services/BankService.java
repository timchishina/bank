package org.example.Services;

import com.google.inject.Inject;
import org.example.Banking.BankAccount;
import org.example.Banking.Facades.AccountFacade;

import java.util.List;

public class BankService {
    private static AccountFacade bankFacade;

    @Inject
    public BankService(AccountFacade bankFacade) {
        this.bankFacade = bankFacade;
    }

    public static AccountFacade getFacade() {
        return BankService.bankFacade;
    }

    public static String getInfo(BankAccount account) {
        return bankFacade.getInfo(account);
    }

    public static List<BankAccount> getAllAccounts() {
        return bankFacade.getAllAccounts();
    }

    public void createAccount(String name, double balance) {
        bankFacade.create(name, balance);
    }

    public boolean accountExists(int id) {
        return bankFacade.find(id) != null;
    }

    public void editAccount(int id, String newName, double newBalance) {
        bankFacade.change(id, newName, newBalance);
    }

    public void deleteAccount(int id) {
        bankFacade.delete(id);
    }
}
