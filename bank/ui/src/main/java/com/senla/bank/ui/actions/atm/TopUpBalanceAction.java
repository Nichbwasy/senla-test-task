package com.senla.bank.ui.actions.atm;

import com.senla.bank.atm.machine.ATMMachine;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;
import com.senla.bank.utils.scaners.ConsoleScanner;

public class TopUpBalanceAction implements UiAction {

    private final ATMMachine atmMachine;

    public TopUpBalanceAction(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void execute() {
        try {
            if (atmMachine.getIsAuthenticated()) {
                ConsoleLog.log("Enter amount: ");
                Double amount = ConsoleScanner.scanDouble();
                atmMachine.topUpBalance(atmMachine.getCurrentCreditCard(), amount);
            } else ConsoleLog.log("Not authenticated!");
        } catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }
    }
}
