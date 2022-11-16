package com.senla.bank.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class BankAccount implements Serializable {

    @NotNull(message = "Bank account number is mandatory!")
    @NotEmpty(message = "Bank account number can not be empty!")
    @Pattern(regexp = "^[0-9]{20}$", message = "Bank account number has incorrect format!")
    private String number;

    @Setter
    @NotNull(message = "Bank account amount is mandatory!")
    private Double amount;

}
