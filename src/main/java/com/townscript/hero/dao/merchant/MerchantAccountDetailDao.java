package com.townscript.hero.dao.merchant;

import java.util.List;

import com.townscript.hero.model.merchant.MerchantAccountDetails;

public interface MerchantAccountDetailDao {

	int addMerchantAccount(MerchantAccountDetails merchantAccountDetails);
	
	int updateMerchantAccount(MerchantAccountDetails merchantAccountDetails);
	
	MerchantAccountDetails loadAccountDetails(int accountId);
	
	List<MerchantAccountDetails> loadAllAccountDetails(int merchantId);
}
