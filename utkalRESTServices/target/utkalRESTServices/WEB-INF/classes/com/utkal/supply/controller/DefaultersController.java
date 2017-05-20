package com.utkal.supply.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.utkal.supply.customer.service.DefaultersService;
import com.utkal.supply.model.JarDefaulter;
import com.utkal.supply.model.PaymentDefaulters;
import com.utkal.supply.model.PreviousPendingDetails;


@Component
@Path("/defaulters")
@scope("prototype");
public class DefaultersController {
	
public DefaultersService defaulterService;
	


	public static Logger logger = Logger.getLogger(DefaultersController.class);
	
	
	/**
	 * Returns the payment defaulters List
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/paymentDefaulterList")
	public List<PaymentDefaulters> getPaymentDefaulters(){
		List<PaymentDefaulters> paymentDefaultersList = new ArrayList<PaymentDefaulters>();
		logger.debug("BEGIN : getPaymentDefaulters()");
		paymentDefaultersList=defaulterService.getPaymentDefaulters();
		return paymentDefaultersList;
	}
	
	

	/**
	 * Returns the Jar defaulters List
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/jarDefaulterList")
	public List<JarDefaulter> getJarDefaulters(){
		List<JarDefaulter> jarDefaulterList = new ArrayList<JarDefaulter>();
		logger.debug("BEGIN : getJarDefaulters()");
		jarDefaulterList=defaulterService.getJarDefaulters();
		logger.debug("END : getJarDefaulters()");
		return jarDefaulterList;
	}
	
	
	/**
	 * Returns the  defaulters List
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/defaulterListTesting")
	public List<PreviousPendingDetails> getDefaulterList(){
		List<PreviousPendingDetails> defaulterList = new ArrayList<PreviousPendingDetails>();
		logger.debug("BEGIN : getDefaulterList()");
		defaulterList=defaulterService.getDefaulterList();
		logger.debug("END : getDefaulterList()");
		return defaulterList;
	}
	
	
	
	
	
	public void setDefaulterService(DefaultersService defaulterService) {
		this.defaulterService = defaulterService;
	}
	
	
}
