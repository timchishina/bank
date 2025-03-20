package org.example.Banking.Facades;

import org.example.Banking.Bank;
import org.example.Banking.BankAccount;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountFacadeTest {

    @Test
    void getAllAccounts() {
        Bank bank = new Bank();
        BankAccount b = new BankAccount();
        Bank.accounts.add(b);
        List<BankAccount> bb = new ArrayList<BankAccount>();
        bb.add(b);
        assertEquals(bb, AccountFacade.getAllAccounts());
    }

    @Test
    void find() {
        Bank bank = new Bank();
        BankAccount b = new BankAccount();
        b.setId(1);
        Bank.accounts.add(b);
        assertEquals(b, AccountFacade.find(b.getId()));
    }

    @Test
    void view() {
        Bank bank = new Bank();
        BankAccount b = new BankAccount();
        b.setId(1);
        Bank.accounts.add(b);
        List<BankAccount> g = new ArrayList<BankAccount>();
        g.add(b);
        assertEquals(g, AccountFacade.getAllAccounts());
    }

    @Test
    void change() {
        Bank bank = new Bank();
        BankAccount b = new BankAccount();
        b.setId(1);
        Bank.accounts.add(b);
        AccountFacade.change(b.getId(), "a", 23.5);
        b.setName("a");
        b.setBalance(23.5);
        assertEquals(b, AccountFacade.find(b.getId()));
    }

    @Test
    void getInfo() {
        BankAccount account = new BankAccount();
        account.setBalance(1);
        account.setId(2);
        account.setName("a");
        String s = account.getId() + "," + account.getName() + "," + account.getBalance();
        assertEquals(s, AccountFacade.getInfo(account));
    }
}