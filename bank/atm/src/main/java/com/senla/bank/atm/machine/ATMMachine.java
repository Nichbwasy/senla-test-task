package com.senla.bank.atm.machine;

public interface ATMMachine {

    String getCurrentCreditCard();
    void setCurrentCreditCard(String card);
    Boolean getIsAuthenticated();
    void setIsAuthenticated(Boolean flag);

    Boolean authenticate(String card, String pin);
    Double checkBalance(String card);
    void withdrawMoney(String card, Double amount);
    void topUpBalance(String card, Double amount);

    void save();


}
