package com.utkal.supply.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.utkal.supply.customer.service.CustomerService;
import com.utkal.supply.customer.service.CustomerServiceImpl;
import com.utkal.supply.customer.service.OrderService;
import com.utkal.supply.customer.service.OrderServiceImpl;
import com.utkal.supply.model.Customer;
import com.utkal.supply.model.Order;

@Component
@Path("/order")
public class OrderController {

	
	
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/newOrder")
	@Consumes(MediaType.APPLICATION_JSON)
	public Order newOrder(Order order) throws Exception{
		
		System.out.println("Order model and customer id is "+ order.getNormalWaterJarOrder());
		OrderService orderService = new OrderServiceImpl();
		System.out.println("order Date is "+ order.getOrderDate());
		orderService.createOrder(order);
	return  order;
	}

	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/customerPaymentDetails/{customerId}")
	public Order getCustomerPaymentDetails(@PathParam("customerId") String customerId){
		System.out.println("customer Id is "+ customerId);
        Order order = new Order();
		OrderService orderService = new OrderServiceImpl();
		order = orderService.getCustomerPaymentDetails(customerId);
		
		
		return order;
	}
	
	
	

	
}
