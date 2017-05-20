package com.utkal.supply.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.utkal.supply.customer.service.OrderService;
import com.utkal.supply.model.Order;
import com.utkal.supply.model.OrderHistory;

@Component
@Path("/order")
public class OrderController {

	
	public static Logger logger = Logger.getLogger(OrderController.class);
	
	
	public OrderService orderService;
	
	
	
	/**
	 * 
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/newOrder")
	@Consumes(MediaType.APPLICATION_JSON)
	public Order newOrder(Order order) throws Exception{
		logger.debug("BEGIN : newOrder()");
		order.setResult(orderService.createOrder(order));
		logger.debug("END : newOrder()");
	return  order;
	}

	
	
	/**
	 * 
	 * 
	 * @param customerId
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/customerPaymentDetails/{customerId}")
	public Order getCustomerPaymentDetails(@PathParam("customerId") String customerId){
		logger.debug("BEGIN : getCustomerPaymentDetails()");
        Order order = new Order();
		order = orderService.getCustomerPaymentDetails(customerId);
		logger.debug("END : getCustomerPaymentDetails()");
		return order;
	}


	
	/**
	 * 
	 * 
	 * @param customerId
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/getOrderByDateAndCustomerId")
	public Order getOrderDetailsByDateAndCustomerId(Order order){
		logger.debug("BEGIN : getOrderDetailsByDateAndCustomerId()");
		System.out.println(order.getCustomerId());
		orderService.getCustomerOrderByDate(order);
		logger.debug("END : getOrderDetailsByDateAndCustomerId()");
		return order;
	}



	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/updateOrder")
	public Order updateOrder(Order order){
		Order orderReturn = new Order();
		logger.debug("BEGIN : updateOrder..");
		orderReturn = orderService.updateOrder(order);
		logger.debug("END : updateOrder..");
		
		
		return orderReturn;
	}
	
	
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/customerOrderHistory")
	public List<Order> getOrderHistory(OrderHistory orderHistory) throws Exception{
		List<Order> orderReturn = new ArrayList<Order>();
		logger.debug("BEGIN : getOrderHistory..");
		orderReturn=orderService.getOrderHistory(orderHistory);
		logger.debug("END : getOrderHistory..");
		
		
		return orderReturn;
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/orderDetailsByDate")
	public List<Order> getOrderDetailsByDate(OrderHistory orderHistory) throws Exception{
		List<Order> orderReturn = new ArrayList<Order>();
		logger.debug("BEGIN : getOrderHistory..");
		orderReturn=orderService.getOrderDetailsByDate(orderHistory);
		logger.debug("END : getOrderHistory..");
		
		
		return orderReturn;
	}
	
	
	
	
	
	
	
	
	


    /**
     * 
     * 
     * @param orderService
     */
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	

	
}
