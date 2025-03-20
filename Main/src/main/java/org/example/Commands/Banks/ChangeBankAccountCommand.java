package org.example.Commands.Banks;

import org.example.Commands.Command;
import org.example.Services.BankService;

public class ChangeBankAccountCommand implements Command {
    private final BankService bankService;
    private final int accountId;
    private final String newName;
    private final double newBalance;

    public ChangeBankAccountCommand(BankService bankService, int accountId, String newName, double newBalance) {
        this.bankService = bankService;
        this.accountId = accountId;
        this.newName = newName;
        this.newBalance = newBalance;
    }

    @Override
    public void execute() {
        if (!bankService.accountExists(accountId)) {
            System.out.println("Ошибка: Счет не найден.");
            return;
        }
        bankService.editAccount(accountId, newName, newBalance);
        System.out.println("Счет ID " + accountId + " обновлен.");
    }
}
