package org.example;
import org.example.Banking.Bank;
import org.example.Banking.BankAccount;
import org.example.Banking.Facades.AccountFacade;
import org.example.Commands.Banks.CreateBankAccountCommand;
import org.example.Commands.Categories.CreateCategoryCommand;
import org.example.Commands.Operations.CreateOperationCommand;
import org.example.Services.BankService;
import org.example.Services.CategoryService;
import org.example.Services.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;


class ClientTest {
    private Bank b = new Bank();
    private BankService bankService;
    private OperationService operationService;
    private CategoryService categoryService;
    private Client client;

    @BeforeEach
    void setUp() {
        bankService = mock(BankService.class);
        operationService = mock(OperationService.class);
        categoryService = mock(CategoryService.class);
        client = new Client(bankService, operationService, categoryService);
    }

    @Test
    void testAddBankAccount() {
        new CreateBankAccountCommand(bankService, "Тестовый счет", 500.0).execute();

        verify(bankService).createAccount(eq("Тестовый счет"), eq(500.0));
    }
    @Test
    void testAddCategory() {
        new CreateCategoryCommand(categoryService, true, "Зарплата").execute();

        verify(categoryService).addCategory(eq(true), eq("Зарплата"));
    }

    @Test
    void testDeleteBankAccount() {
        when(bankService.accountExists(1)).thenReturn(true);

        new org.example.Commands.Banks.DeleteBankAccountCommand(bankService, 1).execute();

        verify(bankService).deleteAccount(eq(1));
    }

    @Test
    void testDeleteOperation() {
        when(operationService.operationExists(1)).thenReturn(true);

        new org.example.Commands.Operations.DeleteOperationCommand(operationService, 1).execute();

        verify(operationService).deleteOperation(eq(1));
    }

    @Test
    void testDeleteCategory() {
        when(categoryService.categoryExists(1)).thenReturn(true);

        new org.example.Commands.Categories.DeleteCategoryCommand(categoryService, 1).execute();

        verify(categoryService).deleteCategory(eq(1));
    }

    @Test
    void testAddCategory_EmptyName() {
        new CreateCategoryCommand(categoryService, true, "").execute();

        verify(categoryService, never()).addCategory(anyBoolean(), eq(""));
    }

    @Test
    void testDeleteOperation_NonExisting() {
        when(operationService.operationExists(99)).thenReturn(false);

        new org.example.Commands.Operations.DeleteOperationCommand(operationService, 99).execute();

        verify(operationService, never()).deleteOperation(anyInt());
    }
}
