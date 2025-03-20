package org.example.Banking.Factories;

import org.example.Banking.Bank;
import org.example.Banking.BankAccount;
import org.example.Banking.Facades.AccountFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountFactoryTest {
    Bank b = new Bank();
    @BeforeEach
    void setUp() {
        Bank.accounts.clear(); // Очистка перед каждым тестом
        AccountFactory.CountAccount = 0; // Сброс счётчика ID
    }
    @Test
    void create() {
        BankAccount account = new BankAccount();
        account.setBalance(1);
        account.setName("a");
        account.setId(0);
        AccountFactory.create(0, "a", 1);
        assertEquals(AccountFacade.getInfo(account), AccountFacade.getInfo(AccountFacade.find(0)));
        AccountFacade.delete(0);
    }

    @Test
    void testCreateAccount_WithNegativeBalance() {
        BankAccount account = AccountFactory.create("Test Account", -100.0);

        assertEquals(0, account.getBalance(), "Баланс не должен быть отрицательным");
    }

    @Test
    void testCreateAccount_WithId_NameAndBalance() {
        BankAccount account1 = AccountFactory.create(5, "Special Account", 1000.0);

        assertNotNull(account1, "Счёт должен быть создан");
        assertEquals("Special Account", account1.getName(), "Название должно совпадать");
        assertEquals(1000.0, account1.getBalance(), "Баланс должен быть установлен");
        assertEquals(5, account1.getId(), "ID должен быть равен переданному ID");
        assertTrue(Bank.accounts.contains(account1), "Счёт должен добавляться в Bank.accounts");
    }
    @Test
    void testCreateAccount_WithNameAndBalance() {
        BankAccount account = AccountFactory.create("Test Account", 500.0);

        assertNotNull(account, "Счёт должен быть создан");
        assertEquals("Test Account", account.getName(), "Название должно совпадать");
        assertEquals(500.0, account.getBalance(), "Баланс должен быть установлен");
        assertEquals(0, account.getId(), "ID должен начинаться с 0");
        assertTrue(Bank.accounts.contains(account), "Счёт должен добавляться в Bank.accounts");
    }
}
