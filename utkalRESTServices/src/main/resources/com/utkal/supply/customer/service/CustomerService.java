package com.utkal.supply.customer.service;

import java.util.List;
import java.util.Map;

import com.utkal.supply.model.Customer;

public interface CustomerService {
	
	public int saveCustomer(Customer customer);
	
	public Map<String,String> getCustomerList();
	
	public List<Customer> getCustomerListJson();
	
	public Customer getCustomerDetails(String customerId);
	
	public int updateCustomer(Customer customer);
	
	public String deleteCustomer(String customerId);

}
