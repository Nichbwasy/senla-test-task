package com.senla.bank.ui.actions.atm;

import com.senla.bank.atm.machine.ATMMachine;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;

public class ATMMachineSaveAndGoBackAction implements UiAction {

    private final ATMMachine atmMachine;

    public ATMMachineSaveAndGoBackAction(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void execute() {
        try {
            atmMachine.setCurrentCreditCard("");
            atmMachine.setIsAuthenticated(false);
            atmMachine.save();
        } catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }
    }
}
