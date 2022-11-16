package com.senla.bank.ui.actions.bank;

import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;
import com.senla.bank.utils.scaners.ConsoleScanner;

public class RemoveCreditCardAction implements UiAction {

    private final BankRepository bankRepository;

    public RemoveCreditCardAction(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void execute() {
        try {
            ConsoleLog.log("Enter credit card number to remove (XXXX-XXXX-XXXX-XXXX format):");
            String card = ConsoleScanner.scanString();
            bankRepository.removeCreditCard(card);
        } catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }
    }
}
