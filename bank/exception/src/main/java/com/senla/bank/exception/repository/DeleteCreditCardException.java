package com.senla.bank.exception.repository;

public class DeleteCreditCardException extends RepositoryException {

    public DeleteCreditCardException() {
    }

    public DeleteCreditCardException(String message) {
        super(message);
    }

    public DeleteCreditCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteCreditCardException(Throwable cause) {
        super(cause);
    }

    public DeleteCreditCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
