package com.utkal.supply.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
public class PreviousPendingDetails {
	
	private String customerId="0";
	private String customerName="";
	private String prevColdJarPending="0";
	private String prevNormalJarPending="0";
	private String prevContainerPending="0";
	private String prevPaymentDue="0";
	private int result=0;
	private boolean isNewForm=true;
	
	
	
	
	
	
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
	public String getPrevColdJarPending() {
		return prevColdJarPending;
	}
	public void setPrevColdJarPending(String prevColdJarPending) {
		this.prevColdJarPending = prevColdJarPending;
	}
	public String getPrevNormalJarPending() {
		return prevNormalJarPending;
	}
	public void setPrevNormalJarPending(String prevNormalJarPending) {
		this.prevNormalJarPending = prevNormalJarPending;
	}
	public String getPrevContainerPending() {
		return prevContainerPending;
	}
	public void setPrevContainerPending(String prevContainerPending) {
		this.prevContainerPending = prevContainerPending;
	}
	public String getPrevPaymentDue() {
		return prevPaymentDue;
	}
	public void setPrevPaymentDue(String prevPaymentDue) {
		this.prevPaymentDue = prevPaymentDue;
	}

	
	
	
	
	
	
}
