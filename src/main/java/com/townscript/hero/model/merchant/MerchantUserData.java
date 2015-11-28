package com.townscript.hero.model.merchant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Proxy(lazy=false)
@Entity
@Table(name="merchant_user_data_table")
public class MerchantUserData{

	private int merchantId;

	@Length(max=45,message="First name should be maximum 45 chars")
	private String firstName;
	
	@Length(max=45,message="Last Name should be maximum 45 chars")
	private String lastName;
	
	@NotEmpty(message="email may not be null or empty")
	@Length(max=100,message="Email Id should be maximum 100 chars")
	private String emailId;
	
	@NotEmpty(message="password may not be null or empty")
	@Length(max=45,message="Password should be maximum 45 chars")
	private String password;

	@Id
	@GeneratedValue
	@Column(name="MERCHANT_ID")
	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
