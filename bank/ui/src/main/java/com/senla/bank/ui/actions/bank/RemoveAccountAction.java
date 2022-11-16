package com.senla.bank.ui.actions.bank;

import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;
import com.senla.bank.utils.scaners.ConsoleScanner;

public class RemoveAccountAction implements UiAction {

    private final BankRepository bankRepository;

    public RemoveAccountAction(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void execute() {
        try {
            ConsoleLog.log("Enter account number to remove (20 digits):");
            String account = ConsoleScanner.scanString();
            bankRepository.removeAccount(account);
        } catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }
    }
}
