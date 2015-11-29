package com.townscript.hero.service.application;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.townscript.hero.dao.application.ApplicationDataDao;
import com.townscript.hero.model.application.ApplicationData;
import com.townscript.hero.model.application.PaymentGatewayMap;

@Transactional
public class ApplicationDataServiceImpl implements ApplicationDataService{

	private ApplicationDataDao applicationDataDao;
	
	
	public ApplicationDataDao getApplicationDataDao() {
		return applicationDataDao;
	}

	public void setApplicationDataDao(ApplicationDataDao applicationDataDao) {
		this.applicationDataDao = applicationDataDao;
	}

	@Override
	public int addApplicationData(ApplicationData applicationData) {
		return applicationDataDao.addApplicationData(applicationData);
	}

	@Override
	public ApplicationData loadApplicationData(int id) {
		return applicationDataDao.loadApplicationData(id);
	}

	@Override
	public List<ApplicationData> loadAllApplicationForMerchant(int merchantId) {
		return applicationDataDao.loadAllApplicationForMerchant(merchantId);
	}

	@Override
	public void deleteApplicationData(int id) {
		applicationDataDao.deleteApplicationData(id);
	}

	@Override
	public int updateApplicationData(ApplicationData applicationData) {
		return applicationDataDao.updateApplicationData(applicationData);
	}

	@Override
	public PaymentGatewayMap loadGatewayMap(int id) {
		return applicationDataDao.loadGatewayMap(id);
	}

}
