package org.example.Commands.Banks;

import org.example.Commands.Command;
import org.example.Services.BankService;

public class CreateBankAccountCommand implements Command {
    private final BankService bankService;
    private final String name;
    private final double balance;
    public CreateBankAccountCommand(BankService bankService, String name, double balance) {
        this.bankService = bankService;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public void execute() {
        bankService.createAccount(name, balance);
        System.out.println("Счет создан: " + name + " с балансом " + balance);
    }
}
