package com.senla.bank.exception.atm;

public class TopUpBalanceException extends ATMException {

    public TopUpBalanceException() {
    }

    public TopUpBalanceException(String message) {
        super(message);
    }

    public TopUpBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopUpBalanceException(Throwable cause) {
        super(cause);
    }

    public TopUpBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
