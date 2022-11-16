package com.senla.bank.exception.validation;

public class CreditCardValidationException extends ValidationException {

    public CreditCardValidationException() {
        super();
    }

    public CreditCardValidationException(String message) {
        super(message);
    }

    public CreditCardValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreditCardValidationException(Throwable cause) {
        super(cause);
    }

    protected CreditCardValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
