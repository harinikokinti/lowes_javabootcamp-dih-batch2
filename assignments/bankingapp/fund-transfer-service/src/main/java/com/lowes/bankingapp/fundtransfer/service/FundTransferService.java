package com.lowes.bankingapp.fundtransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowes.bankingapp.fundtransfer.exception.InsufficientBalanceException;
import com.lowes.bankingapp.fundtransfer.model.Account;
import com.lowes.bankingapp.fundtransfer.model.FundTransfer;

@Service
public class FundTransferService {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	RestTemplate accountRestTemplate;
	
	@Value("{accountServiceHost}")
	private String accountServiceHost;

	// create fund transfer
	public FundTransfer create(FundTransfer fundTransfer) {
//		Account account = accountRestTemplate.getForEntity(
//				"http://localhost:8111/banking/api/accounts/" + fundTransfer.getSourceAccId(), Account.class).getBody();
		
		// here give gateway-service ip at localhost
		Account account = accountRestTemplate.getForEntity(
		"http://172.17.0.4:8111/banking/api/accounts/" + fundTransfer.getSourceAccId(), Account.class).getBody();


	
		// check if source account is active & has enough balance to transfer
		if (fundTransfer.getAmount() <= account.getBalance() && "active".equals(account.getStatus())) {
			String fundTransferDetails = fundTransfer.getSourceAccId() + "," + fundTransfer.getTargetAccId() + ","
					+ fundTransfer.getAmount() + "," + fundTransfer.getDescription();
			kafkaTemplate.send("FUNDTRANSFER_CREATED", fundTransferDetails); // topic, message
			return fundTransfer;
		} else {
			throw new InsufficientBalanceException(
					"No sufficient balance to transfer or Check if the source account is active");
		}

	}

	public void clear() {

	}

}
