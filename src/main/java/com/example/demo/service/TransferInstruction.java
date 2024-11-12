package com.example.demo.service;

import java.math.BigDecimal;

public record TransferInstruction(
        String senderAccount,
        String receiverAccount,
        BigDecimal amount,
        String transactionRef
) {
}
