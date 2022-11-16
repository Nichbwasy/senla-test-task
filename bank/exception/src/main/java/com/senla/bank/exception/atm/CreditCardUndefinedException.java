package com.senla.bank.exception.atm;

public class CreditCardUndefinedException extends ATMException{

    public CreditCardUndefinedException() {
    }

    public CreditCardUndefinedException(String message) {
        super(message);
    }

    public CreditCardUndefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreditCardUndefinedException(Throwable cause) {
        super(cause);
    }

    public CreditCardUndefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
