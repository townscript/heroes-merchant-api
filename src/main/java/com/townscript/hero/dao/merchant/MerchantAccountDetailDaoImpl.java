package com.townscript.hero.dao.merchant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.hero.model.merchant.MerchantAccountDetails;

public class MerchantAccountDetailDaoImpl extends HibernateDaoSupport implements MerchantAccountDetailDao{

	private JdbcTemplate jdbcTemplate;
	
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addMerchantAccount(MerchantAccountDetails merchantAccountDetails) {
		getHibernateTemplate().save(merchantAccountDetails);
		return merchantAccountDetails.getAccountId();
	}

	@Override
	public int updateMerchantAccount(
			MerchantAccountDetails merchantAccountDetails) {
		getHibernateTemplate().merge(merchantAccountDetails);
		return merchantAccountDetails.getAccountId();
	}

	@Override
	public MerchantAccountDetails loadAccountDetails(int accountId) {
		try {
			return getHibernateTemplate().load(MerchantAccountDetails.class, accountId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchantAccountDetails> loadAllAccountDetails(int merchantId) {
		
		List<MerchantAccountDetails> merchantAccountDetails = getHibernateTemplate().find("From "+MerchantAccountDetails.class.getSimpleName()+" account " +
				"where account.merchantId = "+merchantId);
		if (merchantAccountDetails != null && !merchantAccountDetails.isEmpty()) {
			return new ArrayList<MerchantAccountDetails>();
		} else {
			return merchantAccountDetails;
		}
	}

}
