package com.senla.bank.exception.repository;

public class FileWriteException extends RepositoryException {

    public FileWriteException() {
    }

    public FileWriteException(String message) {
        super(message);
    }

    public FileWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileWriteException(Throwable cause) {
        super(cause);
    }

    public FileWriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
