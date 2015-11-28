package com.townscript.hero.dao.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.hero.model.transaction.TransactionDetails;

public class TransactionDetailDaoImpl extends HibernateDaoSupport implements TransactionDetailDao{

	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addTransaction(TransactionDetails transactionDetails) {
		getHibernateTemplate().save(transactionDetails);
		return transactionDetails.getTxnId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransactionDetails> loadTransactionDetails(int merchantId) {
		List<TransactionDetails> transactionDetails = getHibernateTemplate().find("From "+TransactionDetails.class.getSimpleName()+" txn " +
				"where txn.merchantId = "+merchantId);
		if (transactionDetails != null && !transactionDetails.isEmpty()) {
			return transactionDetails;
		} else {
			return new ArrayList<TransactionDetails>();
		}
	}

}
