package com.example.demo.service;

import java.math.BigDecimal;

public class Account {
    private final String accountId;
    private BigDecimal balance;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = BigDecimal.valueOf(balance);
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void debit(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance = balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        balance = balance.add(amount);
    }
}
