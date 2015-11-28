package com.townscript.hero.model.application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Proxy(lazy=false)
@Entity
@Table(name="payment_gateway_map_table")
public class PaymentGatewayMap {

	
	private int mapId;
	
	private int pgId;
	
	private int applicationId;
	
	private String paymentOption;

	@Id
	@GeneratedValue
	@Column(name="MAP_ID")
	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	@Column(name="PAYMENT_GATEWAY_ID")
	public int getPgId() {
		return pgId;
	}

	public void setPgId(int pgId) {
		this.pgId = pgId;
	}

	@Column(name="APPLICATION_ID")
	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	@Column(name="PAYMENT_OPTION")
	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	
	
}
