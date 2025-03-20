package org.example;

import com.google.inject.AbstractModule;
import org.example.Banking.Facades.AccountFacade;
import org.example.Banking.Facades.CategoryFacade;
import org.example.Banking.Facades.OperationFacade;
import org.example.Services.BankService;
import org.example.Services.CategoryService;
import org.example.Services.OperationService;

class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AccountFacade.class).toInstance(new AccountFacade());
        bind(OperationFacade.class).toInstance(new OperationFacade());
        bind(CategoryFacade.class).toInstance(new CategoryFacade());

        bind(BankService.class).toInstance(new BankService(new AccountFacade()));
        bind(OperationService.class).toInstance(new OperationService(new OperationFacade()));
        bind(CategoryService.class).toInstance(new CategoryService(new CategoryFacade()));
    }
}
