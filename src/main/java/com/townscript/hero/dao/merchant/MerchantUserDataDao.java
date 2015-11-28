package com.townscript.hero.dao.merchant;

import com.townscript.hero.model.merchant.MerchantUserData;

public interface MerchantUserDataDao {

	int addMerchant(MerchantUserData userData);
	
	MerchantUserData loadMerchantUserData(int merchantId);
	
	MerchantUserData loadMerchantUserData(String emailId);
}
