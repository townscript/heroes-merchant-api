package com.townscript.hero.service.transaction;

import java.util.List;

import com.townscript.hero.model.transaction.TransactionDetails;

public interface TransactionDetailService {

	int addTransaction(TransactionDetails transactionDetails);
	
	List<TransactionDetails> loadTransactionDetails(int merchantId);
}
