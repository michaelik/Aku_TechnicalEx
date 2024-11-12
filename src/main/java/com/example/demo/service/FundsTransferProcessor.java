package com.example.demo.service;

import java.math.BigDecimal;
import java.util.*;

public class FundsTransferProcessor {
    private final Map<String, Account> accounts = new HashMap<>();
    private final Set<String> transactionReferences = new HashSet<>();
    private List<TransferInstruction> transferInstructions = new ArrayList<>();
    private int successfulTransfers = 0;
    private BigDecimal totalAmountTransferred = BigDecimal.ZERO;
    private final List<String> errors = new ArrayList<>();


    public FundsTransferProcessor(
            List<Account> sampleAccounts,
            List<TransferInstruction> instructions) {
        for (Account account : sampleAccounts) {
            accounts.put(account.getAccountId(), account);
        }
        this.transferInstructions = instructions;
    }

    public void processTransfers() {
        for (TransferInstruction instruction : transferInstructions) {
            try {
                validateAndExecuteTransfer(instruction);
                successfulTransfers++;
                totalAmountTransferred = totalAmountTransferred.add(instruction.amount());
            } catch (Exception e) {
                errors.add("Error processing transaction " + instruction.transactionRef() + ": " + e.getMessage());
            }
        }
    }

    private void validateAndExecuteTransfer(TransferInstruction instruction) {
        if (!transactionReferences.add(instruction.transactionRef())) {
            throw new IllegalArgumentException("Duplicate transaction reference.");
        }

        Account sender = accounts.get(instruction.senderAccount());
        Account receiver = accounts.get(instruction.receiverAccount());
        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Invalid sender or receiver account.");
        }

        if (sender.getBalance().compareTo(instruction.amount()) < 0) {
            throw new IllegalArgumentException("Insufficient funds for account " + sender.getAccountId());
        }

        // Execute transfer
        sender.debit(instruction.amount());
        receiver.credit(instruction.amount());
    }

    public void generateSummary() {
        System.out.println("=== Transfer Summary ===");
        System.out.println("Total Transfers Processed: " + transferInstructions.size());
        System.out.println("Successful Transfers: " + successfulTransfers);
        System.out.println("Total Amount Transferred: €" + totalAmountTransferred);
        System.out.println("Errors Encountered: " + errors.size());
        for (String error : errors) {
            System.out.println(" - " + error);
        }
    }
}
