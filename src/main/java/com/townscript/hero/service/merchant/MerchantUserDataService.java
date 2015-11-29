package com.townscript.hero.service.merchant;

import com.townscript.hero.model.merchant.MerchantUserData;

public interface MerchantUserDataService {

	int addMerchant(MerchantUserData userData);
	
	MerchantUserData loadMerchantUserData(int merchantId);
	
	MerchantUserData loadMerchantUserData(String emailId);
	
	boolean isMerchantExist(String emailId);
}
