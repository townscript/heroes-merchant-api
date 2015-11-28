package com.townscript.hero.service.merchant;

import java.util.List;

import com.townscript.hero.dao.merchant.MerchantAccountDetailDao;
import com.townscript.hero.model.merchant.MerchantAccountDetails;

public class MerchantAccountDetailServiceImpl implements MerchantAccountDetailService{

	private MerchantAccountDetailDao merchantAccountDetailDao;
	
	public MerchantAccountDetailDao getMerchantAccountDetailDao() {
		return merchantAccountDetailDao;
	}

	public void setMerchantAccountDetailDao(
			MerchantAccountDetailDao merchantAccountDetailDao) {
		this.merchantAccountDetailDao = merchantAccountDetailDao;
	}

	@Override
	public int addMerchantAccount(MerchantAccountDetails merchantAccountDetails) {
		return merchantAccountDetailDao.addMerchantAccount(merchantAccountDetails);
	}

	@Override
	public int updateMerchantAccount(
			MerchantAccountDetails merchantAccountDetails) {
		return merchantAccountDetailDao.updateMerchantAccount(merchantAccountDetails);
	}

	@Override
	public MerchantAccountDetails loadAccountDetails(int accountId) {
		return merchantAccountDetailDao.loadAccountDetails(accountId);
	}

	@Override
	public List<MerchantAccountDetails> loadAllAccountDetails(int merchantId) {
		return merchantAccountDetailDao.loadAllAccountDetails(merchantId);
	}

}
