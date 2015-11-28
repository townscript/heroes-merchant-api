package com.townscript.hero.dao.merchant;

import java.util.List;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.hero.model.merchant.MerchantUserData;

public class MerchantUserDataDaoImpl extends HibernateDaoSupport implements MerchantUserDataDao{

	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public int addMerchant(MerchantUserData userData) {
		getHibernateTemplate().save(userData);
		return userData.getMerchantId();
	}


	@Override
	public MerchantUserData loadMerchantUserData(int merchantId) {
		
		try {
			return getHibernateTemplate().load(MerchantUserData.class,merchantId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public MerchantUserData loadMerchantUserData(String email) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
				String hql = "from " + MerchantUserData.class.getSimpleName() +" m where m.emailId= :email";
				List<MerchantUserData> merchantList = session.createQuery(hql)
				.setParameter("email", email)
				.list();
				
		if (merchantList.size() > 1) {
			throw new IllegalStateException(
					"There can't be multiple merchant for given email Id: "
							+ email);
		}
         if(merchantList==null || merchantList.isEmpty()){
        	 return null ;
         }
		return merchantList.get(0);
	}

}
