package com.senla.bank.exception.repository;

public class CreateAccountException extends RepositoryException {
    public CreateAccountException() {
    }

    public CreateAccountException(String message) {
        super(message);
    }

    public CreateAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateAccountException(Throwable cause) {
        super(cause);
    }

    public CreateAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
