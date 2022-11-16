package com.senla.bank.ui.controls.implementations;

import com.senla.bank.ui.controls.UiControl;
import com.senla.bank.ui.menu.ConsoleMenu;
import com.senla.bank.ui.navigators.ConsoleMenuNavigator;
import com.senla.bank.utils.log.ConsoleLog;
import com.senla.bank.utils.scaners.ConsoleScanner;

import java.io.Console;

public class ConsoleMenuControl implements UiControl {

    private ConsoleMenu menu;
    private ConsoleMenuNavigator navigator;

    public ConsoleMenuControl(ConsoleMenu menu, ConsoleMenuNavigator navigator) {
        this.menu = menu;
        this.navigator = navigator;
    }

    @Override
    public void run() {
        navigator.setMenu(menu);
        while (true) {
            navigator.printMenu();
            try {
                Integer index = ConsoleScanner.scanInt();
                navigator.navigate(index);
            } catch (NumberFormatException exception) {
                ConsoleLog.log("Incorrect input, expect a number!");
            }
        }
    }
}
