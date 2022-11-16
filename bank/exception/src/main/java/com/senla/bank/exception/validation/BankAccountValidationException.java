package com.senla.bank.exception.validation;

public class BankAccountValidationException extends ValidationException {

    public BankAccountValidationException() {
        super();
    }

    public BankAccountValidationException(String message) {
        super(message);
    }

    public BankAccountValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankAccountValidationException(Throwable cause) {
        super(cause);
    }

    protected BankAccountValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
