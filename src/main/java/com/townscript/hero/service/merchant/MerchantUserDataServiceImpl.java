package com.townscript.hero.service.merchant;

import org.springframework.transaction.annotation.Transactional;

import com.townscript.hero.dao.merchant.MerchantUserDataDao;
import com.townscript.hero.model.merchant.MerchantUserData;

@Transactional
public class MerchantUserDataServiceImpl implements MerchantUserDataService{

	private MerchantUserDataDao merchantUserDataDao;
	
	
	public MerchantUserDataDao getMerchantUserDataDao() {
		return merchantUserDataDao;
	}


	public void setMerchantUserDataDao(MerchantUserDataDao merchantUserDataDao) {
		this.merchantUserDataDao = merchantUserDataDao;
	}


	@Override
	public int addMerchant(MerchantUserData userData) {
		return merchantUserDataDao.addMerchant(userData);
	}
	
	@Override
	public MerchantUserData loadMerchantUserData(int merchantId) {
		return merchantUserDataDao.loadMerchantUserData(merchantId);
	}


	@Override
	public MerchantUserData loadMerchantUserData(String emailId) {
		return merchantUserDataDao.loadMerchantUserData(emailId);
	}


	@Override
	public boolean isMerchantExist(String emailId) {
		MerchantUserData merchantUserData = merchantUserDataDao.loadMerchantUserData(emailId);
		if (merchantUserData != null) {
			return true;
		}else{
			return false;
		}
		
	}
	
	
//	public static void main(String[] args) {
//		MerchantUserDataService merchantUserDataService = null;
//		
//		if (merchantUserDataService == null) {
//			ApplicationContext context = new ClassPathXmlApplicationContext(
//					"com/townscript/hero/main-bean.xml");
//			merchantUserDataService = (MerchantUserDataService) context
//					.getBean("MerchantUserDataServiceImpl");
//		}
//		
//		MerchantUserData data = new MerchantUserData();
//		data.setEmailId("nagargoje@townscript.com");
//		data.setFirstName("Sachin");
//		data.setLastName("Nagargoje");
//		data.setPassword("password");
//		
//		int id = merchantUserDataService.addMerchant(data);
//		
//		System.out.println(id);
//	}


	

}
