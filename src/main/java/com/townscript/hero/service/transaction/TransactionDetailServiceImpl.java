package com.townscript.hero.service.transaction;

import java.util.List;

import com.townscript.hero.dao.transaction.TransactionDetailDao;
import com.townscript.hero.model.transaction.TransactionDetails;

public class TransactionDetailServiceImpl implements TransactionDetailService{

	private TransactionDetailDao transactionDetailDao;
	
	
	public TransactionDetailDao getTransactionDetailDao() {
		return transactionDetailDao;
	}

	public void setTransactionDetailDao(TransactionDetailDao transactionDetailDao) {
		this.transactionDetailDao = transactionDetailDao;
	}

	@Override
	public int addTransaction(TransactionDetails transactionDetails) {
		return transactionDetailDao.addTransaction(transactionDetails);
	}

	@Override
	public List<TransactionDetails> loadTransactionDetails(int merchantId) {
		return transactionDetailDao.loadTransactionDetails(merchantId);
	}

}
