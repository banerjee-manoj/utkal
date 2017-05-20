package com.utkal.supply.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.utkal.supply.customer.service.CustomerService;
import com.utkal.supply.customer.service.CustomerServiceImpl;
import com.utkal.supply.customer.service.DefaultersService;
import com.utkal.supply.customer.service.DefaultersServiceImpl;
import com.utkal.supply.exception.ApplicationExceptionMapper;
import com.utkal.supply.model.Customer;
 
@Controller
@Path("/customer")
public class CustomerController {

	
	public static Logger logger = Logger.getLogger(CustomerController.class);
	
	public DefaultersService defaulterService;
	public CustomerService customerService;
	
	
	
	
	public String loadNewCustomerPage() {
		
		return "";
	} 
	
	/*
	 * Save a customer in database..
	 * and return the customer details if the data is saved properly.
	 * 
	 */
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/saveNewCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer addNewCustomer(Customer customer){
		logger.debug("BEGIN : addNewCustomer()");
		logger.debug("Customer to be saved is "+ customer.getCustomerName());
	    int result =customerService.saveCustomer(customer);
	   customer.setResult(result);
	   logger.debug("END : addNewCustomer()");
	   return  customer;
	}

	
	
	
	
	
	@Path("/editCustomer")
    public String editCustomer(){
	
	return "";
}

	@Path("/deleteCustomer")
public String deleteCustomer(){
	
	return "";
}

	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/customerList")
	public Map<String,String> getCustomerList(){
		logger.debug("BEGIN : getCustomerList()");
		Map<String,String> customerList = new HashMap<String,String>();
		defaulterService.getPaymentDefaulters();
    	CustomerService customerService = new CustomerServiceImpl();
		customerList=customerService.getCustomerList();
		logger.debug("END : getCustomerList()");
	return customerList;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/customerDetails/{customerId}")
	public Customer getCustomerDetails(@PathParam("customerId") String customerId){
		System.out.println("customer Id is "+ customerId);
		Customer customer = new Customer();
		CustomerService customerService = new CustomerServiceImpl();
		customer = customerService.getCustomerDetails(customerId);
		return customer;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/updateCustomer")
	public Customer updateCustomer(Customer customer){
		logger.debug("BEGIN : updateCustomer() method..");
		System.out.println("Customer to be updated "+ customer.getCustomerId() +"   " + customer.getCustomerName());
		  CustomerService customerService = new CustomerServiceImpl();
		  customerService.updateCustomer(customer);
		return customer;
	}
	
	
	
	
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setDefaulterService(DefaultersService defaulterService) {
		this.defaulterService = defaulterService;
	}

	
}
