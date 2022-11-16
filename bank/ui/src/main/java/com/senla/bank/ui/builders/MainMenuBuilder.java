package com.senla.bank.ui.builders;

import com.senla.bank.ui.actions.main.DoNothingAction;
import com.senla.bank.ui.actions.main.ExitAction;
import com.senla.bank.ui.menu.ConsoleMenu;
import com.senla.bank.ui.menu.MenuItem;

public class MainMenuBuilder {

    public static ConsoleMenu build() {
        ConsoleMenu menu = new ConsoleMenu();
        ConsoleMenu atmMenu = ATMMachineMenuBuilder.build(menu);
        ConsoleMenu bankMenu = BankRepositoryMenuBuilder.build(menu);

        menu.setName("MAIN MENU");

        MenuItem goToATMMenu = new MenuItem("ATM menu", new DoNothingAction(), atmMenu);
        MenuItem goToBankMenu = new MenuItem("Bank menu", new DoNothingAction(), bankMenu);
        MenuItem exit = new MenuItem("Exit", new ExitAction(), null);

        menu.addMenuItem(goToATMMenu);
        menu.addMenuItem(goToBankMenu);
        menu.addMenuItem(exit);

        return menu;
    }

}
