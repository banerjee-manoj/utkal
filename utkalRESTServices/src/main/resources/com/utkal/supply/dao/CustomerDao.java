package com.utkal.supply.dao;

import java.util.List;
import java.util.Map;

import com.utkal.supply.model.Customer;
import com.utkal.supply.model.PreviousPendingDetails;

public interface CustomerDao {

	public int saveCusomer(Customer customer);
	
	public Map<String,String> getCustomerList();
	
	public List<Customer> getCustomerListJson();
	
	public Customer getCustomerDetails(String customerId);
	
	public int updateCustomer(Customer customer);
	
	public PreviousPendingDetails checkForDelete(String customerId);
	
	public String deleteCustomerById(String customerId);
}
