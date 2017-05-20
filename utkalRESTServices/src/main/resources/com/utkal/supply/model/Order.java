package com.utkal.supply.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
public class Order {
	
	
	private String customerId;
	private String customerName;
	private String orderDate;
	private String normalWaterJarOrder="";
	private String normalWaterRate="";
	private int normalWaterJarReturnedFilled=0;
	private String normalWaterJarReturnedEmpty="0";
	private String coldWaterJarOrder="";
	private String coldWaterRate="";
	private int coldWaterJarReturnedFilled=0;
	private int coldWaterJarReturnedEmpty=0;
	private String totalBill="";
	private String paymentRcvd="";
	private String outstandingAmmount="";
	private int normalWaterJarPending=0;
	private int coldWaterJarPending=0;
	private int previousDue=0;
	private int containerPending=0;
	private int containerOrdered=0;
	private int containerReturned=0;
	private int result=0;
	private boolean isNewForm=true;
	
	
	
	
	
	
	
	
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getContainerReturned() {
		return containerReturned;
	}
	public void setContainerReturned(int containerReturned) {
		this.containerReturned = containerReturned;
	}
	public boolean isNewForm() {
		return isNewForm;
	}
	public void setNewForm(boolean isNewForm) {
		this.isNewForm = isNewForm;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getContainerOrdered() {
		return containerOrdered;
	}
	public void setContainerOrdered(int containerOrdered) {
		this.containerOrdered = containerOrdered;
	}
	public int getContainerPending() {
		return containerPending;
	}
	public void setContainerPending(int containerPending) {
		this.containerPending = containerPending;
	}
	public int getNormalWaterJarReturnedFilled() {
		return normalWaterJarReturnedFilled;
	}
	public void setNormalWaterJarReturnedFilled(int normalWaterJarReturnedFilled) {
		this.normalWaterJarReturnedFilled = normalWaterJarReturnedFilled;
	}
	public String getNormalWaterJarReturnedEmpty() {
		return normalWaterJarReturnedEmpty;
	}
	public void setNormalWaterJarReturnedEmpty(String normalWaterJarReturnedEmpty) {
		this.normalWaterJarReturnedEmpty = normalWaterJarReturnedEmpty;
	}
	public int getColdWaterJarReturnedFilled() {
		return coldWaterJarReturnedFilled;
	}
	public void setColdWaterJarReturnedFilled(int coldWaterJarReturnedFilled) {
		this.coldWaterJarReturnedFilled = coldWaterJarReturnedFilled;
	}
	public int getColdWaterJarReturnedEmpty() {
		return coldWaterJarReturnedEmpty;
	}
	public void setColdWaterJarReturnedEmpty(int coldWaterJarReturnedEmpty) {
		this.coldWaterJarReturnedEmpty = coldWaterJarReturnedEmpty;
	}
	public int getPreviousDue() {
		return previousDue;
	}
	public void setPreviousDue(int previousDue) {
		this.previousDue = previousDue;
	}
	public int getNormalWaterJarPending() {
		return normalWaterJarPending;
	}
	public void setNormalWaterJarPending(int normalWaterJarPending) {
		this.normalWaterJarPending = normalWaterJarPending;
	}
	public int getColdWaterJarPending() {
		return coldWaterJarPending;
	}
	public void setColdWaterJarPending(int coldWaterJarPending) {
		this.coldWaterJarPending = coldWaterJarPending;
	}
	public String getOutstandingAmmount() {
		return outstandingAmmount;
	}
	public void setOutstandingAmmount(String outstandingAmmount) {
		this.outstandingAmmount = outstandingAmmount;
	}
	public String getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}
	public String getPaymentRcvd() {
		return paymentRcvd;
	}
	public void setPaymentRcvd(String paymentRcvd) {
		this.paymentRcvd = paymentRcvd;
	}
	
	public String getNormalWaterRate() {
		return normalWaterRate;
	}
	public void setNormalWaterRate(String normalWaterRate) {
		this.normalWaterRate = normalWaterRate;
	}
	/*public String getNormalWaterJarReturned() {
		return normalWaterJarReturnedFilled;
	}*/
	/*public void setNormalWaterJarReturned(String normalWaterJarReturned) {
		this.normalWaterJarReturnedFilled = normalWaterJarReturned;
	}*/
	public String getColdWaterJarOrder() {
		return coldWaterJarOrder;
	}
	public void setColdWaterJarOrder(String coldWaterJarOrder) {
		this.coldWaterJarOrder = coldWaterJarOrder;
	}
	public String getColdWaterRate() {
		return coldWaterRate;
	}
	public void setColdWaterRate(String coldWaterRate) {
		this.coldWaterRate = coldWaterRate;
	}
	/*public String getColdWaterJarReturned() {
		return coldWaterJarReturnedFilled;
	}
	public void setColdWaterJarReturned(String coldWaterJarReturned) {
		this.coldWaterJarReturnedFilled = coldWaterJarReturned;
	}
	*/public String getNormalWaterJarOrder() {
		return normalWaterJarOrder;
	}
	public void setNormalWaterJarOrder(String normalWaterJarOrder) {
		this.normalWaterJarOrder = normalWaterJarOrder;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	

}
