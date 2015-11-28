package com.townscript.hero.model.merchant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;

@Proxy(lazy=false)
@Entity
@Table(name="merchant_account_data_table")
public class MerchantAccountDetails{

	private int accountId;
	
	private int merchantId;
	
	@Length(max=45,message="Account holder name should be maximum 45 chars")
	private String accountHolderName;
	
	@Length(max=45,message="Account number should be maximum 45 chars")
	private String accountNumber;
	
	@Length(max=45,message="IFSC code should be maximum 45 chars")
	private String ifscCode;
	
	@Length(max=45,message="Bank name should be maximum 100 chars")
	private String bankName;

	@Id
	@GeneratedValue
	@Column(name="ACCOUNT_ID")
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Column(name="MERCHANT_ID")
	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name="ACCOUNT_HOLDER_NAME")
	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	@Column(name="ACCOUNT_NUMBER")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name="IFSC_CODE")
	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	@Column(name="BANK_NAME")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
}
