package com.senla.bank.exception.atm;

public class CreditCardAuthenticateException extends ATMException {
    public CreditCardAuthenticateException() {
    }

    public CreditCardAuthenticateException(String message) {
        super(message);
    }

    public CreditCardAuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreditCardAuthenticateException(Throwable cause) {
        super(cause);
    }

    public CreditCardAuthenticateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
