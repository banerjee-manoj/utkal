package com.utkal.supply.dao;

import java.util.List;

import com.utkal.supply.model.JarDefaulter;
import com.utkal.supply.model.PaymentDefaulters;

public interface DefaultersDao {

	
	public List<PaymentDefaulters> getPaymentDefaulters();
	public List<JarDefaulter> getJarDefaulters();
}
