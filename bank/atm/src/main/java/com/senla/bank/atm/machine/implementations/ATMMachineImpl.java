package com.senla.bank.atm.machine.implementations;

import com.senla.bank.atm.machine.ATMMachine;
import com.senla.bank.exception.atm.*;
import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.repository.dao.implementations.BankRepositoryImpl;
import com.senla.bank.utils.log.ConsoleLog;

import java.util.Date;

public class ATMMachineImpl implements ATMMachine {

    private BankRepository bankRepository;
    private Double machineFunds;
    private String currentCreditCard = "";
    private Boolean isAuthenticated = false;
    private final static Double TOP_UP_LIMIT = 1000000.0;

    public ATMMachineImpl(Double machineFunds) {
        this.bankRepository = new BankRepositoryImpl();
        this.machineFunds = machineFunds;
    }

    public Boolean authenticate(String card, String pin) {
        bankRepository.load();
        if (bankRepository.checkCardExist(card)) {
            checkCardBlockStatus(card);
            return authenticateAttempt(card, pin);
        } else {
            ConsoleLog.warn("Credit card '%s' is undefined!", card);
            throw new CreditCardUndefinedException(String.format("Credit card '%s' is undefined!", card));
        }
    }

    private void checkCardBlockStatus(String card) throws ATMException {
        // Check credit card block status and it expiration
        if (bankRepository.checkCardBlock(card)) {
            Date now = new Date();
            Date expiration = bankRepository.getBlockExpirationData(card);
            if (now.after(expiration)) {
                bankRepository.unblockCreditCard(card);
                ConsoleLog.info("Block for the credit card '%s' was expired! Card has been unblocked.", card);
            } else {
                ConsoleLog.warn("Credit card '%s' is blocked! Retry later.", card);
                throw new CreditCardBlockedException(String.format("Credit card '%s' is blocked! Retry later.", card));
            }
        }
    }

    private Boolean authenticateAttempt(String card, String pin) {
        //Authentication process
        ConsoleLog.info("Attempt to authenticate credit card '%s'...", card);
        Integer authenticateAttempts = bankRepository.getAuthenticationAttempts(card);
        if (bankRepository.verifyCardPin(card, pin)) {
            ConsoleLog.info("Credit card '%s' was successful authenticated.", card);
            return true;
        } else {
            ConsoleLog.info("Unsuccessful authenticated for the credit card '%s'. Card number or PIN doesn'r match!", card);
            authenticateAttempts++;
            if (authenticateAttempts >= 3) {
                ConsoleLog.info("Too many attempts to authenticate! The credit card '%s' will be blocked!", card);
                bankRepository.blockCreditCard(card);
            }
            bankRepository.setAuthenticationAttempts(card, authenticateAttempts);
            return false;
        }
    }

    @Override
    public Double checkBalance(String card) {
        if (bankRepository.checkCardExist(card)) {
            Double balance = bankRepository.getMoney(card);
            ConsoleLog.info("Balance for the credit card '%s': %f", card, balance);
            return balance;
        } else {
            ConsoleLog.warn("Credit card '%s' is undefined!", card);
            throw new CreditCardUndefinedException(String.format("Credit card '%s' is undefined!", card));
        }
    }

    public void withdrawMoney(String card, Double amount) {
        if (bankRepository.checkCardExist(card)) {
            Double currentBalance = bankRepository.getMoney(card);
            if (amount <= currentBalance) {
                if (amount <= machineFunds) {
                    machineFunds =- amount;
                    bankRepository.setMoney(card, currentBalance - amount);
                    ConsoleLog.info("Money '%f' has been transfer to the credit card '%s' bank account successfully.", amount, card);
                } else {
                    ConsoleLog.warn("ATM machine has not enough fonds!");
                    throw new NotEnoughFundsException("ATM machine has not enough fonds!");
                }
            } else {
                ConsoleLog.warn("Not enough founds to withdraw '%f' money from '%s' credit card!", amount, card);
                throw new InsufficientFundsException(
                        String.format("Not enough founds to withdraw '%f' money from '%s' credit card!", amount, card)
                );
            }
        } else {
            ConsoleLog.warn("Credit card '%s' is undefined!", card);
            throw new CreditCardUndefinedException(String.format("Credit card '%s' is undefined!", card));
        }
    }

    public void topUpBalance(String card, Double amount) {
        if (bankRepository.checkCardExist(card)) {
            if (amount <= TOP_UP_LIMIT) {
                Double currentBalance = bankRepository.getMoney(card);
                machineFunds =+ amount;
                bankRepository.setMoney(card, currentBalance + amount);
            } else {
                ConsoleLog.warn("The money amount exceeds top uo limit!");
                throw new TopUpBalanceException("The money amount exceeds top uo limit!");
            }
        } else {
            ConsoleLog.warn("Credit card '%s' is undefined!", card);
            throw new CreditCardUndefinedException(String.format("Credit card '%s' is undefined!", card));
        }
    }

    @Override
    public void save() {
        bankRepository.save();
        bankRepository.load();
    }

    public String getCurrentCreditCard() {
        return currentCreditCard;
    }

    public void setCurrentCreditCard(String currentCreditCard) {
        this.currentCreditCard = currentCreditCard;
    }

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}
