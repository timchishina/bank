package org.example.Banking;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    @Test
    void getType() {
        Operation op = new Operation();
        op.setType(true);
        assertEquals(true, op.getType());
    }

    @Test
    void getDescription() {
        Operation op = new Operation();
        op.setDescription("a");
        assertEquals("a", op.getDescription());
    }

    @Test
    void getBank_account_id() {
        Operation op = new Operation();
        op.setBank_account_id(0);
        assertEquals(0, op.getBank_account_id());
    }

    @Test
    void getCategory_id() {
        Operation op = new Operation();
        op.setCategory_id(0);
        assertEquals(0, op.getCategory_id());
    }

    @Test
    void getId() {
        Operation op = new Operation();
        op.setId(0);
        assertEquals(0, op.getId());
    }

    @Test
    void getDate() {
        Operation op = new Operation();
        LocalDateTime d = LocalDateTime.now();
        op.setDate(d);
        assertEquals(d, op.getDate());
    }

    @Test
    void getSum() {
        Operation op = new Operation();
        op.setSum(3);
        assertEquals(3, op.getSum());
    }
}