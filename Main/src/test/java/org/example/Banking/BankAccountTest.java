package org.example.Banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getId() {
        BankAccount b = new BankAccount();
        b.setId(2);
        assertEquals(b.getId(), 2);
    }

    @Test
    void getBalance() {
        BankAccount b = new BankAccount();
        b.setBalance(2);
        assertEquals(2, b.getBalance());
    }

    @Test
    void getName() {
        BankAccount b = new BankAccount();
        b.setName("a");
        assertEquals("a", b.getName());
    }
}