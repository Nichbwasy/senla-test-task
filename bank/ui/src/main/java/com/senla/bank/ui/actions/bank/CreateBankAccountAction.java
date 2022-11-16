package com.senla.bank.ui.actions.bank;

import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.repository.entities.BankAccount;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;
import com.senla.bank.utils.scaners.ConsoleScanner;
import com.senla.bank.utils.validators.DefaultValidator;

public class CreateBankAccountAction implements UiAction {

    private final BankRepository bankRepository;

    public CreateBankAccountAction(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void execute() {
        try {
            ConsoleLog.log("Enter new account number (20 digits):");
            String number = ConsoleScanner.scanString();
            ConsoleLog.log("Enter new account amount:");
            Double amount = ConsoleScanner.scanDouble();
            BankAccount newAccount = new BankAccount(number, amount);
            DefaultValidator.validate(newAccount);
            bankRepository.createAccount(newAccount);
        } catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }
    }
}
