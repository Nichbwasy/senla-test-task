package com.senla.bank.ui.actions.bank;

import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.repository.entities.CreditCard;
import com.senla.bank.ui.actions.UiAction;
import com.senla.bank.utils.log.ConsoleLog;
import com.senla.bank.utils.scaners.ConsoleScanner;
import com.senla.bank.utils.validators.DefaultValidator;

public class CreateCreditCardAction implements UiAction {

    private final BankRepository bankRepository;

    public CreateCreditCardAction(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void execute() {
        try {
            ConsoleLog.log("Enter account number (20 digits):");
            String account = ConsoleScanner.scanString();
            ConsoleLog.log("Enter credit card number (XXXX-XXXX-XXXX-XXXX format):");
            String number = ConsoleScanner.scanString();
            ConsoleLog.log("Enter PIN-code (4 digits):");
            String pin = ConsoleScanner.scanString();
            CreditCard creditCard = new CreditCard(account, number, pin);
            DefaultValidator.validate(creditCard);
            bankRepository.createCreditCard(creditCard);
        }   catch (Exception e) {
            ConsoleLog.error(e.getClass() + ": " + e.getMessage());
        }
    }
}
