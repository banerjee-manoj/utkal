package com.utkal.supply.dao;

import java.util.Map;

import com.utkal.supply.model.Customer;

public interface CustomerDao {

	public String saveCusomer(Customer customer);
	
	public Map<String,String> getCustomerList();
	
	public Customer getCustomerDetails(String customerId);
	
	public String updateCustomer(Customer customer);
}
