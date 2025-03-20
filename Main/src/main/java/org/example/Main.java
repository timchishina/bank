package org.example;

import org.example.Banking.Bank;
import org.example.Banking.Facades.AccountFacade;
import org.example.Banking.Facades.CategoryFacade;
import org.example.Banking.Facades.OperationFacade;
import org.example.Services.BankService;
import org.example.Services.CategoryService;
import org.example.Services.OperationService;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        AccountFacade af = new AccountFacade();
        CategoryFacade cf = new CategoryFacade();
        OperationFacade of = new OperationFacade();
        OperationService os = new OperationService(of);
        CategoryService cs = new CategoryService(cf);
        BankService bk = new BankService(af);
        Client client = new Client(bk, os, cs);
        client.run();
    }
}