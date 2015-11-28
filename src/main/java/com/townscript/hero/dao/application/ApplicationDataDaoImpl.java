package com.townscript.hero.dao.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.hero.model.application.ApplicationData;
import com.townscript.hero.model.application.PaymentGatewayMap;

public class ApplicationDataDaoImpl extends HibernateDaoSupport implements ApplicationDataDao{

	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addApplicationData(ApplicationData applicationData) {
		getHibernateTemplate().save(applicationData);
		return applicationData.getAppId();
	}

	@Override
	public ApplicationData loadApplicationData(int id) {
		try {
			return getHibernateTemplate().load(ApplicationData.class, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationData> loadAllApplicationForMerchant(int merchantId) {
		List<ApplicationData> applList = getHibernateTemplate().find("from "+ApplicationData.class.getSimpleName()+ " a where " +
				"a.merchantId="+merchantId);
		
		if (applList != null && !applList.isEmpty()) {
			return new ArrayList<ApplicationData>();
		} else {
			return applList;
		}
		
	}

	@Override
	public void deleteApplicationData(int id) {
		ApplicationData applicationData = loadApplicationData(id);
		if (applicationData != null) {
			getHibernateTemplate().delete(applicationData);
		}
		
	}

	@Override
	public int updateApplicationData(ApplicationData applicationData) {
		getHibernateTemplate().merge(applicationData);
		return applicationData.getAppId();
	}

	@Override
	public PaymentGatewayMap loadGatewayMap(int id) {
		try {
			return getHibernateTemplate().load(PaymentGatewayMap.class, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
