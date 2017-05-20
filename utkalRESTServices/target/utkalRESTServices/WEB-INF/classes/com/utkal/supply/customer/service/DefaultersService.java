package com.utkal.supply.customer.service;

import java.util.List;

import com.utkal.supply.model.JarDefaulter;
import com.utkal.supply.model.PaymentDefaulters;

public interface DefaultersService {
	
	public List<PaymentDefaulters> getPaymentDefaulters();
	public List<JarDefaulter> getJarDefaulters();

}
