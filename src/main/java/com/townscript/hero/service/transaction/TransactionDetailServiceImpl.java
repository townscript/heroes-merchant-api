package com.townscript.hero.service.transaction;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.townscript.hero.dao.transaction.TransactionDetailDao;
import com.townscript.hero.model.transaction.TransactionDetails;

@Transactional
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
