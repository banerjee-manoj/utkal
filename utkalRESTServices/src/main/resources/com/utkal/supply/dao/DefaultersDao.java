package com.utkal.supply.dao;

import java.util.List;

import com.utkal.supply.model.JarDefaulter;
import com.utkal.supply.model.PaymentDefaulters;
import com.utkal.supply.model.PreviousPendingDetails;

public interface DefaultersDao {

	
	public List<PaymentDefaulters> getPaymentDefaulters();
	public List<JarDefaulter> getJarDefaulters();
	public List<PreviousPendingDetails> getDefaulterList();
	public PreviousPendingDetails getPrevDtlsByCustomer(String customerId);
	public PreviousPendingDetails savePrevDtls(PreviousPendingDetails prevDtls);
}
