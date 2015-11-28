package com.townscript.hero.dao.transaction;

import java.util.List;

import com.townscript.hero.model.transaction.TransactionDetails;

public interface TransactionDetailDao {

	int addTransaction(TransactionDetails transactionDetails);
	
	List<TransactionDetails> loadTransactionDetails(int merchantId);
	
}
