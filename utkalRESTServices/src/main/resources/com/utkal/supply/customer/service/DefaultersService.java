package com.utkal.supply.customer.service;

import java.util.List;

import com.utkal.supply.model.JarDefaulter;
import com.utkal.supply.model.PaymentDefaulters;
import com.utkal.supply.model.PreviousPendingDetails;

public interface DefaultersService {
	
	public List<PaymentDefaulters> getPaymentDefaulters();
	public List<JarDefaulter> getJarDefaulters();
	public List<PreviousPendingDetails> getDefaulterList();
	public PreviousPendingDetails getPreviousDueDetailsByCustomer(String customerId);
	public PreviousPendingDetails savePendingDtls(PreviousPendingDetails prevDtls);

}
