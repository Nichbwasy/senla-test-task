package com.senla.bank.ui.actions.atm;

import com.senla.bank.atm.machine.ATMMachine;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;
import com.senla.bank.utils.scaners.ConsoleScanner;

public class AuthenticateAction implements UiAction {

    private final ATMMachine atmMachine;

    public AuthenticateAction(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void execute() {
        try {
            ConsoleLog.log("Enter credit card number (XXXX-XXXX-XXXX-XXXX format):");
            String card = ConsoleScanner.scanString();
            ConsoleLog.log("Enter credit card PIN-code (4 digits): ");
            String pin = ConsoleScanner.scanString();
            if (atmMachine.authenticate(card, pin)) {
                atmMachine.setCurrentCreditCard(card);
                atmMachine.setIsAuthenticated(true);
            } else {
                atmMachine.setCurrentCreditCard("");
                atmMachine.setIsAuthenticated(false);
            }
        } catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }

    }
}
