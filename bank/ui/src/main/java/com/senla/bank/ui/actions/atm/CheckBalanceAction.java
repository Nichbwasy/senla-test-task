package com.senla.bank.ui.actions.atm;

import com.senla.bank.atm.machine.ATMMachine;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;

public class CheckBalanceAction implements UiAction {

    private final ATMMachine atmMachine;

    public CheckBalanceAction(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void execute() {
        try {
            StringBuilder result = new StringBuilder();
            if (atmMachine.getIsAuthenticated())
                result.append("Current balance: " + atmMachine.checkBalance(atmMachine.getCurrentCreditCard()));
            else result.append("Not authenticated!");
            ConsoleLog.log(result.toString());
        } catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }
    }
}
