package com.utkal.supply.model;

public class PaymentDefaulters {
	
	private int customerId;
	private  String customerName;
	private String mobileNumber;
	private String paymentDue;
	//public int 
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPaymentDue() {
		return paymentDue;
	}
	public void setPaymentDue(String paymentDue) {
		this.paymentDue = paymentDue;
	}
	
	

}
