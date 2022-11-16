package com.senla.bank.run;

import com.senla.bank.ui.builders.MainMenuBuilder;
import com.senla.bank.ui.controls.UiControl;
import com.senla.bank.ui.controls.implementations.ConsoleMenuControl;
import com.senla.bank.ui.menu.ConsoleMenu;
import com.senla.bank.ui.navigators.ConsoleMenuNavigator;

public class RunApplication {

    public static void main(String[] args) {
        ConsoleMenu menu = MainMenuBuilder.build();
        ConsoleMenuNavigator navigator = new ConsoleMenuNavigator(menu);
        UiControl menuControl = new ConsoleMenuControl(menu, navigator);
        menuControl.run();
    }

}
