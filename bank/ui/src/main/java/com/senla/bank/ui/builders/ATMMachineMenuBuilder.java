package com.senla.bank.ui.builders;

import com.senla.bank.atm.machine.ATMMachine;
import com.senla.bank.atm.machine.implementations.ATMMachineImpl;
import com.senla.bank.ui.actions.atm.*;
import com.senla.bank.ui.menu.ConsoleMenu;
import com.senla.bank.ui.menu.MenuItem;

public class ATMMachineMenuBuilder {

    private final static ATMMachine atmMachine = new ATMMachineImpl(10000D);

    public static ConsoleMenu build() {

        ConsoleMenu menu = new ConsoleMenu();
        ConsoleMenu authenticatedMenu = new ConsoleMenu();

        authenticatedMenu.setName("SELECT OPERATION");
        MenuItem checkBalance = new MenuItem("Check balance", new CheckBalanceAction(atmMachine), null);
        MenuItem withdrawMoney = new MenuItem("Withdraw money", new WithdrawMoneyAction(atmMachine), null);
        MenuItem topUpBalance = new MenuItem("Top up balance", new TopUpBalanceAction(atmMachine), null);
        MenuItem goBackToAuthenticate = new MenuItem("Go back", new ATMMachineSaveAndGoBackAction(atmMachine), menu);

        menu.setName("WELCOME");
        MenuItem authenticate = new MenuItem("Enter credit card", new AuthenticateAction(atmMachine), authenticatedMenu);

        authenticatedMenu.addMenuItem(checkBalance);
        authenticatedMenu.addMenuItem(withdrawMoney);
        authenticatedMenu.addMenuItem(topUpBalance);
        authenticatedMenu.addMenuItem(goBackToAuthenticate);

        menu.addMenuItem(authenticate);

        return menu;
    }

    public static ConsoleMenu build(ConsoleMenu previousMenu) {

        ConsoleMenu menu = build();

        MenuItem goBack = new MenuItem("Save and go back", new ATMMachineSaveAndGoBackAction(atmMachine), previousMenu);

        menu.addMenuItem(goBack);

        return menu;
    }


}
