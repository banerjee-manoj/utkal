package com.utkal.supply.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.utkal.supply.dao.OrderDao;
import com.utkal.supply.model.Order;
import com.utkal.supply.model.OrderHistory;
import com.utkal.supply.utils.UtkalApplicationUtility;

public class OrderServiceImpl implements OrderService{
	
	public static Logger logger = Logger.getLogger(OrderServiceImpl.class);
	
	public OrderDao orderDao;

	@Override
	public int createOrder(Order order) throws Exception {
          logger.debug("BEGIN : createOrder()");
          int result =0; 
	      //correct date format to be inserted
	     order.setOrderDate(UtkalApplicationUtility.getFormattedDate(order.getOrderDate()));
	      
	  result =   orderDao.createOrder(order);
         orderDao.makePayment(order);
	     logger.debug("END : createOrder()");
		return result;
	}

	@Override
	public Order getCustomerPaymentDetails(String customerId) {
		logger.debug("BEGIN : getCustomerPaymentDetails()");
       Order order= new Order();
		order = orderDao.getOrderDetailsByCustomerId(customerId);
		order.setCustomerId(customerId);
		logger.debug("BEGIN : getCustomerPaymentDetails()");
		return orderDao.getPrevJarOrderDetailsByCustomerId(order);
		
	}
	
	@Override
	public Order updateOrder(Order order){
		logger.debug("BEGIN : updateOrder()...");
		
		return orderDao.updateOrder(order);
		
		
		
		
		
		
	}
	
	
	
	

	/**
	 * 
	 * 
	 * 
	 * @param orderDao
	 */
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public Order getCustomerOrderByDate(Order order) {

		
		return orderDao.getCustomerOrderByDate(order);
	}

	@Override
	public List<Order> getOrderHistory(OrderHistory orderHistory) {
             logger.debug("BEGIN : getOrderHistory()");
             List<Order> orderList = new ArrayList<Order>();
             try{
             orderList = orderDao.getOrderHistory(orderHistory);
             }catch(Exception ex){
            	 logger.error("ERROR occured at getOrderHistory()"+ ex.getMessage());
             }
             logger.debug("END : getOrderHistory()");
		
		return orderList;
	}

	@Override
	public List<Order> getOrderDetailsByDate(OrderHistory orderHistory) {
		  logger.debug("BEGIN : getOrderHistory()");
          List<Order> orderList = new ArrayList<Order>();
          try{
          orderList = orderDao.getOrderDetailsBydate(orderHistory);
          }catch(Exception ex){
         	 logger.error("ERROR occured at getOrderHistory()"+ ex.getMessage());
          }
          logger.debug("END : getOrderHistory()");
		
		return orderList;
	}

	
	
}
