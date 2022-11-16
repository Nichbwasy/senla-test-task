package com.senla.bank.repository.dao.implementations;

import com.senla.bank.exception.repository.*;
import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.repository.entities.BankAccount;
import com.senla.bank.repository.entities.CreditCard;
import com.senla.bank.utils.log.ConsoleLog;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class BankRepositoryImpl implements BankRepository {

    private Set<BankAccount> accounts;
    private Set<CreditCard> cards;

    public BankRepositoryImpl() {
        accounts = new HashSet<>();
        cards = new HashSet<>();
        load();
    }

    @Override
    public Boolean checkAccountExist(String account) {
        load();
        return accounts.stream().anyMatch(a -> a.getNumber().equals(account));
    }

    @Override
    public Boolean checkCardExist(String cardNumber) {
        load();
        return cards.stream().anyMatch(c -> c.getNumber().equals(cardNumber));
    }

    @Override
    public Boolean verifyCardPin(String cardNumber, String pin) {
        load();
        return cards.stream().anyMatch(c -> (c.getNumber().equals(cardNumber) && c.getPin().equals(pin)));
    }

    @Override
    public Boolean checkCardBlock(String cardNumber) {
        load();
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        cards.stream()
                .filter(c -> c.getNumber().equals(cardNumber))
                .findFirst().ifPresent(c -> result.set(c.getIsBlocked()));
        return result.get();
    }

    @Override
    public BankAccount createAccount(BankAccount bankAccount) {
        if (!checkAccountExist(bankAccount.getNumber())) {
            accounts.add(bankAccount);
            ConsoleLog.info("A new bank account has been created successfully.");
            save();
            return bankAccount;
        } else {
            ConsoleLog.warn("Account with this number already exist!");
            throw new CreateAccountException("Account with this number already exist!");
        }
    }

    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        if (!checkCardExist(creditCard.getNumber())) {
            if (checkAccountExist(creditCard.getBankAccountNumber())) {
                ConsoleLog.info("A new credit card has been created successfully.");
                cards.add(creditCard);
                save();
                return creditCard;
            } else {
                ConsoleLog.warn("Account with this number not exist!");
                throw new CreateCreditCardException("Account with this number not exist!");
            }
        } else {
            ConsoleLog.warn("Credit card with this number '%s' already exist!", creditCard.getNumber());
            throw new CreateCreditCardException(String.format("Credit card with this number '%s' already exist!", creditCard.getNumber()));
        }
    }

    @Override
    public void removeAccount(String account) {
        if (checkAccountExist(account)) {
            accounts.removeIf(a -> a.getNumber().equals(account));
            ConsoleLog.info("Account '%s' has been removed.", account);
            save();
        }   else {
            ConsoleLog.warn("Account with this number not exist!");
            throw new DeleteAccountException("Account with this number not exist!");
        }
    }

    @Override
    public void removeCreditCard(String cardNumber) {
        if (checkCardExist(cardNumber)) {
            cards.removeIf(a -> a.getNumber().equals(cardNumber));
            ConsoleLog.info("Credit card '%s' has been removed.", cardNumber);
            save();
        }   else {
            ConsoleLog.warn("Credit card with this number not exist!");
            throw new DeleteCreditCardException("Credit card with this number not exist!");
        }
    }

    @Override
    public void setMoney(String cardNumber, Double amount) {
        if (checkCardExist(cardNumber)) {
            AtomicReference<String> bankAccountNumber = new AtomicReference<>("");
            cards.stream()
                    .filter(c -> c.getNumber().equals(cardNumber))
                    .findFirst().ifPresent(c -> bankAccountNumber.set(c.getBankAccountNumber()));
            accounts.stream()
                    .filter(a -> a.getNumber().equals(bankAccountNumber.get()))
                    .findFirst().ifPresent(a -> a.setAmount(amount));
            ConsoleLog.info("Money has been set for the bank account.");
            save();
        } else {
            ConsoleLog.warn("Credit card with this number '%s' not exist!", cardNumber);
            throw new TransactionException(String.format("Credit card with this number '%s' not exist!", cardNumber));
        }
    }

    @Override
    public Double getMoney(String cardNumber) {
        if (checkCardExist(cardNumber)) {
            AtomicReference<String> bankAccountNumber = new AtomicReference<>("");
            cards.stream()
                    .filter(c -> c.getNumber().equals(cardNumber))
                    .findFirst().ifPresent(c -> bankAccountNumber.set(c.getBankAccountNumber()));
            AtomicReference<Double> amount = new AtomicReference<>(0D);
            accounts.stream()
                    .filter(a -> a.getNumber().equals(bankAccountNumber.toString()))
                    .findFirst().ifPresent(a -> amount.set(a.getAmount()));
            ConsoleLog.info("Money has been got from the bank account.");
            return amount.get();
        } else {
            ConsoleLog.warn("Credit card with this number '%s' not exist!", cardNumber);
            throw new TransactionException(String.format("Credit card with this number '%s' not exist!", cardNumber));
        }
    }

    @Override
    public Date getBlockExpirationData(String cardNumber) {
        if (checkCardExist(cardNumber)) {
            AtomicReference<Date> date = new AtomicReference<>();
            cards.stream()
                    .filter(c -> c.getNumber().equals(cardNumber))
                    .findFirst().ifPresent(c -> date.set(c.getBlockExpiration()));
            return date.get();
        } else {
            ConsoleLog.warn("Credit card with this number '%s' not exist!", cardNumber);
            throw new TransactionException(String.format("Credit card with this number '%s' not exist!", cardNumber));
        }
    }

    @Override
    public String getAllAccountsInfo() {
        load();
        StringBuilder result = new StringBuilder();
        result.append("[BANK ACCOUNTS]\n\n");
        if (accounts.size() > 0) {
            accounts.forEach(a -> result.append(
                    "Account number: " + a.getNumber() + "\n" +
                    "Account amount: " + a.getAmount() + "\n\n"
            ));
        } else result.append("No one bank account has been found!\n");
        return result.toString();
    }

    @Override
    public String getAllCreditCardsInfo() {
        load();
        StringBuilder result = new StringBuilder();
        result.append("[CREDIT CARDS]\n\n");
        if (cards.size() > 0) {
            cards.forEach(c -> result.append(
                    "Credit card number: " + c.getNumber() + "\n" +
                    "Credit card bank account: " + c.getBankAccountNumber() + "\n" +
                    "Credit card PIN-code: " + c.getPin() + "\n" +
                    "Credit card authentication attempts: " + c.getAuthenticationAttempts() + "\n" +
                    "Credit card block status: " + c.getIsBlocked() + "\n" +
                    "Credit card block expiration: " + c.getBlockExpiration() + "\n\n"
            ));
        } else result.append("No one credit card has been found!\n");
        return result.toString();
    }

    @Override
    public void blockCreditCard(String cardNumber) {
        if (checkCardExist(cardNumber)) {
            cards.stream()
                    .filter(c -> c.getNumber().equals(cardNumber))
                    .findFirst().ifPresent(c -> {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.DATE, 1);
                        c.setIsBlocked(true);
                        c.setBlockExpiration(calendar.getTime());
                    });
            ConsoleLog.info("Credit card '%s' has been blocked.", cardNumber);
            save();
        } else {
            ConsoleLog.warn("Credit card with this number '%s' not exist!", cardNumber);
            throw new TransactionException(String.format("Credit card with this number '%s' not exist!", cardNumber));
        }
    }

    @Override
    public void unblockCreditCard(String cardNumber) {
        if (checkCardExist(cardNumber)) {
            cards.stream()
                    .filter(c -> c.getNumber().equals(cardNumber))
                    .findFirst().ifPresent(c -> {
                        c.setIsBlocked(false);
                        c.setBlockExpiration(null);
                        c.setAuthenticationAttempts(0);
                    });
            ConsoleLog.info("Credit card '%s' has been unblocked.", cardNumber);
            save();
        } else {
            ConsoleLog.warn("Credit card with this number '%s' not exist!", cardNumber);
            throw new TransactionException(String.format("Credit card with this number '%s' not exist!", cardNumber));
        }
    }

    @Override
    public Integer getAuthenticationAttempts(String cardNumber) {
        if (checkCardExist(cardNumber)) {
            AtomicReference<Integer> attempts = new AtomicReference<>(0);
            cards.stream()
                    .filter(c -> c.getNumber().equals(cardNumber))
                    .findFirst().ifPresent(c -> attempts.set(c.getAuthenticationAttempts()));
            save();
            return attempts.get();
        } else {
            ConsoleLog.warn("Credit card with this number '%s' not exist!", cardNumber);
            throw new CreateCreditCardException(String.format("Credit card with this number '%s' not exist!", cardNumber));
        }
    }

    @Override
    public void setAuthenticationAttempts(String cardNumber, Integer value) {
        if (checkCardExist(cardNumber)) {
            cards.stream()
                    .filter(c -> c.getNumber().equals(cardNumber))
                    .findFirst().ifPresent(c -> c.setAuthenticationAttempts(value));
            save();
        } else {
            ConsoleLog.warn("Credit card with this number '%s' not exist!", cardNumber);
            throw new CreateCreditCardException(String.format("Credit card with this number '%s' not exist!", cardNumber));
        }
    }

    @Override
    public void save() {
        try {
            File accountsFile = new File("accounts.txt");
            File cardsFile = new File("cards.txt");
            if (accountsFile.createNewFile()) ConsoleLog.info("New 'accounts.txt' file has been created.");
            if (cardsFile.createNewFile()) ConsoleLog.info("New 'cards.txt' file has been created.");

            FileOutputStream accountsFOS = new FileOutputStream(accountsFile);
            FileOutputStream cardsFOS = new FileOutputStream(cardsFile);

            accountsFOS.flush();
            cardsFOS.flush();

            ObjectOutputStream accountsOOS = new ObjectOutputStream(accountsFOS);
            ObjectOutputStream cardsOOS = new ObjectOutputStream(cardsFOS);

            accountsOOS.writeObject(accounts);
            ConsoleLog.info("Bank account was saved into 'accounts.txt' file successfully.");

            cardsOOS.writeObject(cards);
            ConsoleLog.info("Credit cards was saved into 'cards.txt' file successfully.");

            ConsoleLog.info("Closing all files streams...");
            accountsFOS.close();
            accountsOOS.close();
            cardsFOS.close();
            cardsOOS.close();
        } catch (IOException e) {
            ConsoleLog.error("Error was occurred while saving data! " + e.getMessage());
            throw new FileWriteException("Error was occurred while saving data! " + e.getMessage());
        }
    }

    @Override
    public void load() {
        File accountsFile = new File("accounts.txt");
        File cardsFile = new File("cards.txt");

        try {
            if (accountsFile.exists()) {
                FileInputStream accountsFIS = new FileInputStream(accountsFile);
                ObjectInputStream accountsOIS = new ObjectInputStream(accountsFIS);

                accounts = (HashSet<BankAccount>) accountsOIS.readObject();
                ConsoleLog.info("Bank account was loaded from 'accounts.txt' file.");

                accountsFIS.close();
                accountsOIS.close();
            }
            if (cardsFile.exists()) {
                FileInputStream cardsFIS = new FileInputStream(cardsFile);
                ObjectInputStream cardsOIS = new ObjectInputStream(cardsFIS);

                cards = (HashSet<CreditCard>) cardsOIS.readObject();
                ConsoleLog.info("Credit cards was loaded from 'cards.txt' file.");

                cardsFIS.close();
                cardsOIS.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            ConsoleLog.error("Error was occurred while loading data! " + e.getMessage());
            throw new FileWriteException("Error was occurred while loading data! " + e.getMessage());
        }
    }
}
