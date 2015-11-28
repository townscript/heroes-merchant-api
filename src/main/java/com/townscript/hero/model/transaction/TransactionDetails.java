package com.townscript.hero.model.transaction;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;

@Proxy(lazy=false)
@Entity
@Table(name="transaction_data_table")
public class TransactionDetails{

	private int txnId;
	
	@Length(max=100,message="UniqueOrderId should be maximum 100 chars")
	private String uniqueOrderId;
	
	@Length(max=45,message="User name should be maximum 45 chars")
	private String userName;
	
	@Length(max=45,message="User Email Id should be maximum 45 chars")
	private String userEmailId;
	
	@NotNull(message="Amount should not be null")
	@DecimalMax(value = "999999999.99", message = "The amount should be of maximum 999999999.99")
	private float txnAmount;

	private String currency;
	
	private String paymentGatewayId;
	
	private String phoneNumber;
	
	private String transactionStatus ;
	
	private Date transactionTimestamp;
	
	private String transactionSource;
	
	private int paymentSolutionId;
	
	private int merchantId;
	
	
	@Id
	@GeneratedValue
	@Column(name="TXN_ID")
	public int getTxnId() {
		return txnId;
	}

	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}

	@Column(name="UNIQUE_ORDER_ID")
	public String getUniqueOrderId() {
		return uniqueOrderId;
	}

	public void setUniqueOrderId(String uniqueOrderId) {
		this.uniqueOrderId = uniqueOrderId;
	}

	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="USER_EMAIL")
	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	@Column(name="TXN_AMOUNT")
	public float getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(float txnAmount) {
		this.txnAmount = txnAmount;
	}

	@Column(name="CURRENCY")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name="PAYMENT_GATEWAY_ID")
	public String getPaymentGatewayId() {
		return paymentGatewayId;
	}

	public void setPaymentGatewayId(String paymentGatewayId) {
		this.paymentGatewayId = paymentGatewayId;
	}

	@Column(name="TXN_STATUS")
	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Column(name="TXN_TIMESTAMP")
	public Date getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(Date transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

	@Column(name="TXN_SOURCE")
	public String getTransactionSource() {
		return transactionSource;
	}

	public void setTransactionSource(String transactionSource) {
		this.transactionSource = transactionSource;
	}

	@Column(name="USER_PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name="TXN_PG_ID")
	public int getPaymentSolutionId() {
		return paymentSolutionId;
	}

	public void setPaymentSolutionId(int paymentSolutionId) {
		this.paymentSolutionId = paymentSolutionId;
	}

	@Column(name="MERCHANT_ID")
	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	
	
}
