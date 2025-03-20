package org.example.Banking.Factories;

import org.example.Banking.Bank;
import org.example.Banking.BankAccount;
import org.example.Banking.Facades.*;
import org.example.Banking.Operation;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class OperationFactoryTest {
    Bank b = new Bank();

    @BeforeEach
    void setUp() {
        Bank.accounts.clear();
        Bank.operations.clear();
        OperationFactory.CountOperation = 0;
    }

    @Test
    public void testCreateOperation_Income() {
        BankAccount account = AccountFacade.create("Счёт", 1000);
        int accountId = account.getId();

        Operation operation = OperationFactory.create(true, accountId, 500, "Зарплата", 1);

        assertNotNull(operation);
        assertTrue(operation.getType());
        assertEquals(500, operation.getSum());
        assertEquals(accountId, operation.getBank_account_id());
        assertTrue(Bank.operations.containsKey(accountId));
    }

    @Test
    public void testCreateOperation_Expense_EnoughBalance() {
        BankAccount account = AccountFacade.create("Счёт", 1000);
        int accountId = account.getId();

        Operation operation = OperationFactory.create(false, accountId, 200, "Покупка", 2);

        assertNotNull(operation);
        assertFalse(operation.getType());
        assertEquals(200, operation.getSum());
        assertEquals(800, AccountFacade.find(accountId).getBalance());
    }

    @Test
    public void testCreateOperation_Expense_NotEnoughBalance() {
        BankAccount account = AccountFacade.create("Счёт", 100);
        int accountId = account.getId();

        Operation operation = OperationFactory.create(false, accountId, 200, "Покупка", 2);

        assertNull(operation);
    }
}
