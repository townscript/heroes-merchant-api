package com.townscript.hero.model.application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Proxy(lazy=false)
@Entity
@Table(name="application_data_table")
public class ApplicationData {

	private int appId;
	
	private int merchantId;
	
	private String tokenId;
	
	private String deviceSource;
	
	@Length(max=100,message="Success url should be maximum 100 chars")
	private String successUrl;
	
	@Length(max=100,message="Failure url should be maximum 100 chars")
	private String failureUrl;

	@Valid
	@NotEmpty(message="No payment gateway mapping found in application")
	private List<PaymentGatewayMap> paymentGatewayMaps = new ArrayList<PaymentGatewayMap>();
	
	@Id
	@GeneratedValue
	@Column(name="APP_ID")
	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	@Column(name="MERCHANT_ID")
	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name="TOKEN_ID")
	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	@Column(name="DEVICE_SOURCE")
	public String getDeviceSource() {
		return deviceSource;
	}

	public void setDeviceSource(String deviceSource) {
		this.deviceSource = deviceSource;
	}

	@Column(name="SUCCESS_URL")
	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	@Column(name="FAILURE_URL")
	public String getFailureUrl() {
		return failureUrl;
	}

	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="APPLICATION_ID")
	@Fetch(value = FetchMode.SUBSELECT)
	@LazyCollection(value =LazyCollectionOption.FALSE)
	@Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public List<PaymentGatewayMap> getPaymentGatewayMaps() {
		return paymentGatewayMaps;
	}

	public void setPaymentGatewayMaps(List<PaymentGatewayMap> paymentGatewayMaps) {
		this.paymentGatewayMaps = paymentGatewayMaps;
	}
	
	
}
