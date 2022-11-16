package com.senla.bank.ui.actions.bank;

import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;

public class ShowAllCreditCardsAction implements UiAction {

    private final BankRepository bankRepository;

    public ShowAllCreditCardsAction(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void execute() {
        try {
            ConsoleLog.log(bankRepository.getAllCreditCardsInfo());
        } catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }
    }

}
