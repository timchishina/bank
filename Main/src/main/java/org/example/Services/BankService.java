package org.example.Services;

import com.google.inject.Inject;
import org.example.Banking.Facades.AccountFacade;

public class BankService {
    private final AccountFacade bankFacade;

    @Inject
    public BankService(AccountFacade bankFacade) {
        this.bankFacade = bankFacade;
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
