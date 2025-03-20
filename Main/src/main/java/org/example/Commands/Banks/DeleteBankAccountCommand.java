package org.example.Commands.Banks;

import org.example.Commands.Command;
import org.example.Services.BankService;

public class DeleteBankAccountCommand implements Command {
    private final BankService bankService;
    private final int accountId;

    public DeleteBankAccountCommand(BankService bankService, int accountId) {
        this.bankService = bankService;
        this.accountId = accountId;
    }

    @Override
    public void execute() {
        if (!bankService.accountExists(accountId)) {
            System.out.println("Ошибка: Счет не найден.");
            return;
        }
        bankService.deleteAccount(accountId);
        System.out.println("Счет ID " + accountId + " удален.");
    }
}
