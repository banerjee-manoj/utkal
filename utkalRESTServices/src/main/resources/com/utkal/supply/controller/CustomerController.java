package com.utkal.supply.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.utkal.supply.customer.service.CustomerService;
import com.utkal.supply.customer.service.DefaultersService;
import com.utkal.supply.model.Customer;
 
@Component
@Path("/customer")
public class CustomerController {

	
	public static Logger logger = Logger.getLogger(CustomerController.class);
	
	public CustomerService customerService;
	public DefaultersService defaulterService;
	
	
	
    
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public String loadNewCustomerPage() {
		
		return "";
	} 
	
	
	/**
	 * 
	 * 
	 * @param customer
	 * @return
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

	
	
	
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	@Path("/editCustomer")
    public String editCustomer(){
	
	return "";
}

	/**
	 * 
	 * 
	 *
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/deleteCustomer/{customerId}")
public String deleteCustomer(@PathParam("customerId") String customerId) throws Exception{
	
		logger.debug("START : deleteCustomer()");
		System.out.println("customer to be deleted : "+customerId );
		String message = customerService.deleteCustomer(customerId);
		logger.debug("END : deleteCustomer()");
           ObjectMapper object = new ObjectMapper();
           String jsonString = object.writeValueAsString(message);
		return jsonString;
}
	

	
	
	
	
    /**
     * 
     * 
     * 
     * @return
     */
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/customerList")
	public Map<String,String> getCustomerList(){
		logger.debug("BEGIN : getCustomerList()");
		Map<String,String> customerList = new HashMap<String,String>();
		customerList=customerService.getCustomerList();
		logger.debug("END : getCustomerList()");
	return customerList;
	}
	

	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/customerListJson")
	public List<Customer> getCustomerListJson(){
		logger.debug("BEGIN : getCustomerListJson()");
		List<Customer> customerList = new ArrayList<Customer>();
		customerList=customerService.getCustomerListJson();
		logger.debug("END : getCustomerListJson()");
	return customerList;
	}
	
	
	
	
	/**
	 * 
	 * 
	 * 
	 * @param customerId
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/customerDetails/{customerId}")
	public Customer getCustomerDetails(@PathParam("customerId") String customerId){
		System.out.println("customer Id is "+ customerId);
		Customer customer = new Customer();
		//CustomerService customerService = new CustomerServiceImpl();
		customer = customerService.getCustomerDetails(customerId);
		return customer;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param customer
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/updateCustomer")
	public Customer updateCustomer(Customer customer){
		logger.debug("BEGIN : updateCustomer() method..");
		int result=0;
		logger.debug("Customer to be updated "+ customer.getCustomerId() +"   " + customer.getCustomerName());
		result= customerService.updateCustomer(customer);
		customer.setResult(result);
		logger.debug("END : updateCustomer() method..");
		return customer;
	}

	/**
	 * 
	 * 
	 * @param customerService
	 */
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public void setDefaulterService(DefaultersService defaulterService) {
		this.defaulterService = defaulterService;
	}
	
}
