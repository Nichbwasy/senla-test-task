package com.senla.bank.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;


@Getter
public class CreditCard implements Serializable {


    @NotNull(message = "Bank account number is mandatory!")
    @NotEmpty(message = "Bank account can not be empty!")
    @Pattern(regexp = "^[0-9]{20}$", message = "Bank account number has incorrect format!")
    private String bankAccountNumber;

    @NotNull(message = "Card number is mandatory!")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$", message = "Card number has incorrect format!")
    private String number;

    @NotNull(message = "PIN-code is mandatory!")
    @Pattern(regexp = "^[0-9]{4}$", message = "PIN-code has incorrect format!")
    private String pin;

    @Setter
    private Integer authenticationAttempts;

    @Setter
    private Boolean isBlocked;

    @Setter
    private Date blockExpiration;

    public CreditCard(String bankAccountNumber, String number,  String pin) {
        this.bankAccountNumber = bankAccountNumber;
        this.number = number;
        this.pin = pin;
        this.authenticationAttempts = 0;
        this.isBlocked = false;
        this.blockExpiration = null;
    }
}
