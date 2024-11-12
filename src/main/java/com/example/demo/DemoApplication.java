package com.example.demo;

import com.example.demo.service.Account;
import com.example.demo.service.FundsTransferProcessor;
import com.example.demo.service.TransferInstruction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		List<Account> sampleAccounts = Arrays.asList(
				new Account("ACCT_Aku123", 1000.0),new Account("ACCT_Aku456", 2000.0)
		);

		List<TransferInstruction> instructions = List.of(
                new TransferInstruction(
                        "ACCT_Aku123",
                        "ACCT_Aku456",
                        BigDecimal.valueOf(150.0),
                        "AKU001"),
				new TransferInstruction(
						"ACCT_Aku456",
						"ACCT_Aku123",
						BigDecimal.valueOf(300.0),
						"AKU002")
        );

		FundsTransferProcessor processor = new FundsTransferProcessor(sampleAccounts, instructions);
		processor.processTransfers();
		processor.generateSummary();
	}

}
