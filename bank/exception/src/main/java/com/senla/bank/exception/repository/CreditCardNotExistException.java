package com.senla.bank.exception.repository;

public class CreditCardNotExistException extends RepositoryException {

    public CreditCardNotExistException() {
    }

    public CreditCardNotExistException(String message) {
        super(message);
    }

    public CreditCardNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreditCardNotExistException(Throwable cause) {
        super(cause);
    }

    public CreditCardNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
