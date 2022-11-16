package com.senla.bank.ui.actions.main;

import com.senla.bank.ui.actions.UiAction;

public class ExitAction implements UiAction {

    @Override
    public void execute() {
        System.exit(0);
    }
}
