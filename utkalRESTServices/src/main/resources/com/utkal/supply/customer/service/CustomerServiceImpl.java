package com.utkal.supply.customer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


import com.utkal.supply.dao.CustomerDao;
import com.utkal.supply.model.Customer;
import com.utkal.supply.model.PreviousPendingDetails;

@Service
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
		customerNames = customerDao.getCustomerList();
		      for(String key: customerNames.keySet()){
		    	  if(customer.getCustomerName().equalsIgnoreCase(customerNames.get(key))){
		    		  result =2;
		    		  return result;
		    	  }
		      }
		/*if (customerNames.containsValue(customer.getCustomerName())) {
			result = 2;
		}*/ /*else {*/
			result =customerDao.saveCusomer(customer);
		//}
		logger.debug("END :  SaveCustomer() and result is " + result);
		return result;
	}
	
	
	
	
	
     /**
      * 
      * 
      * 
      */
	@Override
	public Map<String,String> getCustomerList() {
		Map<String,String> customerList = new HashMap<String,String>();
		customerList=customerDao.getCustomerList();
		return customerList;
	}

	/**
	 * 
	 * 
	 */
	@Override
	public Customer getCustomerDetails(String customerId) {
		return customerDao.getCustomerDetails(customerId);
	}

	/**
	 * 
	 * 
	 */
	@Override
	public int updateCustomer(Customer customer) {
		return customerDao.updateCustomer(customer);
	}






   /**
    * 
    * 
    * @param customerDao
    */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}





	@Override
	public List<Customer> getCustomerListJson() {
		List<Customer> customerList = new ArrayList<Customer>();
		customerList=customerDao.getCustomerListJson();
		return customerList;
	}





	@Override
	public String deleteCustomer(String customerId) {
		int coldJarPending = 0;
		int normalJarPending = 0;
		int paymentDue = 0;
		int containerPending =0;
		String Message = "";
		//check if customer can be deleted or not...
		PreviousPendingDetails pendingDetails = customerDao.checkForDelete(customerId);
		
		if(null != pendingDetails.getPrevColdJarPending() && Integer.parseInt(pendingDetails.getPrevColdJarPending())>0)
			coldJarPending =Integer.parseInt(pendingDetails.getPrevColdJarPending());
		if(null != pendingDetails.getPrevNormalJarPending() && Integer.parseInt(pendingDetails.getPrevNormalJarPending())>0)
			 normalJarPending = Integer.parseInt(pendingDetails.getPrevNormalJarPending());
		if(null != pendingDetails.getPrevContainerPending() &&  Integer.parseInt(pendingDetails.getPrevContainerPending())>0)
			containerPending = Integer.parseInt(pendingDetails.getPrevContainerPending());
		if(null != pendingDetails.getPrevPaymentDue() && Integer.parseInt(pendingDetails.getPrevPaymentDue()) >0 )
			paymentDue = Integer.parseInt(pendingDetails.getPrevPaymentDue());
		
		if(coldJarPending >0 || normalJarPending >0 || containerPending>0 || paymentDue >0){
			Message ="Customer : "+pendingDetails.getCustomerName()+" can not be deleted as the Customer is in Defaulter List.";
		}else{
			
			//Delete the customer and set the message
			String result = customerDao.deleteCustomerById(customerId);
			Message= "Customer has been deleted.";
				
				
				
				
		}
			
		
		return Message;
	}
	
	

}
