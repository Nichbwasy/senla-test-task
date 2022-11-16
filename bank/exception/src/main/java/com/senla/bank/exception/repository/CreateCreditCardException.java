package com.senla.bank.exception.repository;

public class CreateCreditCardException extends RepositoryException {
    public CreateCreditCardException() {
    }

    public CreateCreditCardException(String message) {
        super(message);
    }

    public CreateCreditCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateCreditCardException(Throwable cause) {
        super(cause);
    }

    public CreateCreditCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
