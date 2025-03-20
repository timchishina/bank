package org.example.Banking.Facades;

import org.example.Banking.Bank;
import org.example.Banking.Operation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OperationFacadeTest {

    @Test
    void find() {
        Bank bank = new Bank();
        Operation op = new Operation();
        List<Operation> oper = new ArrayList<Operation>();
        oper.add(op);
        bank.operations.put(0, oper);
        assertEquals(op, OperationFacade.find(0));
    }

    @Test
    void change() {
        Bank bank = new Bank();
        Operation op = new Operation();
        op.setId(0);
        List<Operation> oper = new ArrayList<Operation>();
        oper.add(op);
        bank.operations.put(0, oper);
        OperationFacade.change(op, true, 0, 23, "a", 0);
        op.setDescription("a");
        op.setType(true);
        op.setSum(23);
        op.setBank_account_id(0);
        op.setCategory_id(1);
        OperationFacade.change(op, true, 0, 23, "a", 1);
        assertEquals(op, OperationFacade.find(0));
    }

    @Test
    void getInfo() {
        Operation operation = new Operation();
        operation.setDescription("a");
        operation.setType(true);
        operation.setSum(23);
        operation.setBank_account_id(0);
        operation.setCategory_id(1);
        String s = operation.getType() + "," + operation.getBank_account_id() + "," + operation.getSum() + "," + operation.getDescription() + "," + operation.getCategory_id();
        assertEquals(s, OperationFacade.getInfo(operation));
    }

    @Test
    void getAllOperations() {
        Bank bank = new Bank();
        Operation op = new Operation();
        List<Operation> oper = new ArrayList<Operation>();
        oper.add(op);
        bank.operations.put(0, oper);
        assertEquals(oper, OperationFacade.getAllOperations());
    }
}