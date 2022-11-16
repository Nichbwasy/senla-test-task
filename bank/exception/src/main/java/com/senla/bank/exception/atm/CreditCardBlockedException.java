package com.senla.bank.exception.atm;

public class CreditCardBlockedException extends ATMException {

    public CreditCardBlockedException() {
    }

    public CreditCardBlockedException(String message) {
        super(message);
    }

    public CreditCardBlockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreditCardBlockedException(Throwable cause) {
        super(cause);
    }

    public CreditCardBlockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
