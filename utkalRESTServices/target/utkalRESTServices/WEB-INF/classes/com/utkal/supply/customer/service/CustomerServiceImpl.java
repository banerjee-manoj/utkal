package com.utkal.supply.customer.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.utkal.supply.dao.CustomerDao;
import com.utkal.supply.dao.CustomerDaoImpl;
import com.utkal.supply.model.Customer;

public class CustomerServiceImpl implements CustomerService {

	public static Logger logger = Logger.getLogger(CustomerServiceImpl.class);
	
	public CustomerDao customerDao;

	
	/**
	 * Save the customer Details to Database.
	 * 
	 */
	
	@Override
	public int saveCustomer(Customer customer) {
		logger.debug("BEGIN : saveCustomer()");
		int result = 0;
		Map<String, String> customerNames = new HashMap<String, String>();
		// CustomerDao customerDao = new CustomerDaoImpl();
		customerNames = customerDao.getCustomerList();
		if (customerNames.containsValue(customer.getCustomerName())) {
			result = 0;
		} else {
			customerDao.saveCusomer(customer);
			result = 1;
		}
		logger.debug("END :  SaveCustomer() and result is " + result);
		return result;
	}
	
	
	
	
	

	@Override
	public Map<String,String> getCustomerList() {
		Map<String,String> customerList = new HashMap<String,String>();
		CustomerDao customerDao = new CustomerDaoImpl();
		customerList=customerDao.getCustomerList();
		
		
		return customerList;
	}

	@Override
	public Customer getCustomerDetails(String customerId) {
		CustomerDao customerDao = new CustomerDaoImpl();
		return customerDao.getCustomerDetails(customerId);
	}

	@Override
	public String updateCustomer(Customer customer) {
		CustomerDao customerDao = new CustomerDaoImpl();
		
		return customerDao.updateCustomer(customer);
	}







	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	

}
