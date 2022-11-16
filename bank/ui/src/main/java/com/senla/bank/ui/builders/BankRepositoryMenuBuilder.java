package com.senla.bank.ui.builders;

import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.repository.dao.implementations.BankRepositoryImpl;
import com.senla.bank.ui.actions.bank.*;
import com.senla.bank.ui.menu.ConsoleMenu;
import com.senla.bank.ui.menu.MenuItem;

public class BankRepositoryMenuBuilder {

    private final static BankRepository bankRepository = new BankRepositoryImpl();

    public static ConsoleMenu build() {

        ConsoleMenu menu = new ConsoleMenu();

        menu.setName("BANK MENU");
        MenuItem showAccounts = new MenuItem("Show all bank accounts", new ShowAllBankAccountsAction(bankRepository), null);
        MenuItem showCreditCards = new MenuItem("Show all credit cards", new ShowAllCreditCardsAction(bankRepository), null);
        MenuItem crateBankAccount = new MenuItem("Create bank account", new CreateBankAccountAction(bankRepository), null);
        MenuItem createCreditCard = new MenuItem("Create credit card", new CreateCreditCardAction(bankRepository), null);
        MenuItem removeBankAccount = new MenuItem("Remove bank account", new RemoveAccountAction(bankRepository), null);
        MenuItem removeCreditCard = new MenuItem("Remove credit card", new RemoveCreditCardAction(bankRepository), null);

        menu.addMenuItem(showAccounts);
        menu.addMenuItem(showCreditCards);
        menu.addMenuItem(crateBankAccount);
        menu.addMenuItem(createCreditCard);
        menu.addMenuItem(removeBankAccount);
        menu.addMenuItem(removeCreditCard);

        return menu;
    }

    public static ConsoleMenu build(ConsoleMenu previousMenu) {

        ConsoleMenu menu = build();
        MenuItem saveAndGoBack = new MenuItem("Save and go back", new BankMenuSaveAndGoBackAction(bankRepository), previousMenu);

        menu.addMenuItem(saveAndGoBack);

        return menu;
    }

}
