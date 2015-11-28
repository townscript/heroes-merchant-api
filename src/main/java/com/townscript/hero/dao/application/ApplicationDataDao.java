package com.townscript.hero.dao.application;

import java.util.List;

import com.townscript.hero.model.application.ApplicationData;
import com.townscript.hero.model.application.PaymentGatewayMap;

public interface ApplicationDataDao {

	int addApplicationData(ApplicationData applicationData);
	
	ApplicationData loadApplicationData(int id);
	
	List<ApplicationData> loadAllApplicationForMerchant(int merchantId);
	
	void deleteApplicationData(int id);
	
	int updateApplicationData(ApplicationData applicationData);
	
	PaymentGatewayMap loadGatewayMap(int id);
}
