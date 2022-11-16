package com.senla.bank.ui.actions.bank;

import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;

public class BankMenuSaveAndGoBackAction implements UiAction {

    private final BankRepository bankRepository;

    public BankMenuSaveAndGoBackAction(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void execute() {
        try {
            ConsoleLog.log("Saving...");
            bankRepository.save();
        } catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }
    }
}
