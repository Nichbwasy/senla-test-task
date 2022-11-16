package com.senla.bank.repository.dao;

import com.senla.bank.repository.entities.BankAccount;
import com.senla.bank.repository.entities.CreditCard;

import java.util.Date;

public interface BankRepository {

    Boolean checkAccountExist(String account);
    Boolean checkCardExist(String cardNumber);
    Boolean checkCardBlock(String cardNumber);
    Boolean verifyCardPin(String cardNumber, String pin);
    BankAccount createAccount(BankAccount bankAccount);
    CreditCard createCreditCard(CreditCard creditCard);
    Integer getAuthenticationAttempts(String cardNumber);
    Double getMoney(String cardNumber);
    Date getBlockExpirationData(String cardNumber);
    String getAllAccountsInfo();
    String getAllCreditCardsInfo();
    void removeAccount(String account);
    void removeCreditCard(String cardNumber);
    void setMoney(String cardNumber, Double amount);
    void setAuthenticationAttempts(String cardNumber, Integer value);
    void blockCreditCard(String cardNumber);
    void unblockCreditCard(String cardNumber);
    void save();
    void load();

}
