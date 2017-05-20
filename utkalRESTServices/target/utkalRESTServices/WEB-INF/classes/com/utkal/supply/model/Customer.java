package com.utkal.supply.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
public class Customer {
	
	
	private String customerId;
	private String customerName="";
	private String address="";
	private String mobileNumber="";
	private String customerType="";
	private String activationDate="";
	@JsonIgnore
	private String securityDeposit="";
	
	private String isContainerRequired;
	
	private int normalJarRate=0;
	private int coldJarRate=0;
	
	private int result;
	
	
	
	
	
	public int getNormalJarRate() {
		return normalJarRate;
	}
	public void setNormalJarRate(int normalJarRate) {
		this.normalJarRate = normalJarRate;
	}
	public int getColdJarRate() {
		return coldJarRate;
	}
	public void setColdJarRate(int coldJarRate) {
		this.coldJarRate = coldJarRate;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getIsContainerRequired() {
		return isContainerRequired;
	}
	public void setIsContainerRequired(String isContainerRequired) {
		this.isContainerRequired = isContainerRequired;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}
	public String getSecurityDeposit() {
		return securityDeposit;
	}
	public void setSecurityDeposit(String securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
	
	
	
	

}
