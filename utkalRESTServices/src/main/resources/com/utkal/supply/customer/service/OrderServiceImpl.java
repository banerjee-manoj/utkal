package com.utkal.supply.customer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.utkal.supply.dao.OrderDao;
import com.utkal.supply.model.Order;
import com.utkal.supply.model.OrderHistory;
import com.utkal.supply.model.TotalCounts;
import com.utkal.supply.utils.UtkalApplicationUtility;

public class OrderServiceImpl implements OrderService{
	
	public static Logger logger = Logger.getLogger(OrderServiceImpl.class);
	
	public OrderDao orderDao;
	
	public DefaultersService defaulterService;

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
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Order updateOrder(Order order) throws Exception{
		logger.debug("BEGIN : updateOrder()...");
		
		//first check if the order is already there for that date or not.
		//if the there is no order then insert the order else update the order.
 		order.setNewForm(orderDao.isNewOrder(order));
 	    order.getPrevDetails().setNewForm(orderDao.isNewPrevOrder(order));
 		
 	    // Update the order... in the jar table.
 	    
 		 
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
	public TotalCounts getOrderDetailsByDate(OrderHistory orderHistory) {
		  logger.debug("BEGIN : getOrderDetailsByDate() "+ new Date());
          List<Order> orderList = new ArrayList<Order>();
          TotalCounts totalCounts = new TotalCounts();
          try{
          orderList = orderDao.getOrderDetailsBydate(orderHistory);
          int totalNormalWaterJarCount = 0;
          int totalNormalJarReturned =0;
          int totalNormalJarFilledReturned =0;
          int totalColdJarOrdered=0;
          int totalColdJarReturned=0;
          int totalColdJarFilledReturned=0;
          int totalContainerOrdered=0;
          int totalContainerReturned=0;
          int totalBillForDtRange =0;
          int totalPayment=0;
          
          for(Order order : orderList){
        	  totalNormalWaterJarCount = totalNormalWaterJarCount+Integer.parseInt(order.getNormalWaterJarOrder());
        	  totalNormalJarReturned=totalNormalJarReturned+Integer.parseInt(order.getNormalWaterJarReturnedEmpty());
        	  totalNormalJarFilledReturned=totalNormalJarFilledReturned+order.getNormalWaterJarReturnedFilled();
        	  
        	  totalColdJarOrdered = totalColdJarOrdered + Integer.parseInt(order.getColdWaterJarOrder());
        	  totalColdJarReturned = totalColdJarReturned+order.getColdWaterJarReturnedEmpty();
        	  totalColdJarFilledReturned = totalColdJarFilledReturned+order.getColdWaterJarReturnedFilled();
        	  
        	  totalContainerOrdered = totalContainerOrdered+order.getContainerOrdered();
        	  totalContainerReturned = totalContainerReturned+order.getContainerReturned();
        	  
        	  totalBillForDtRange=totalBillForDtRange+Integer.parseInt(order.getTotalBill());
        	  
        	  totalPayment=totalPayment+Integer.parseInt(order.getPaymentRcvd());
        	  
        	  
          }
          
          
          
          totalCounts.setOrderList(orderList);
          totalCounts.setTotalBill(totalBillForDtRange);
          
          totalCounts.setTotalNormalJarOrdered(totalNormalWaterJarCount);
          totalCounts.setTotalNormalJarReturnedEmpty(totalNormalJarReturned);
          totalCounts.setTotalNormalJarReturnedFilled(totalNormalJarFilledReturned);
          
          totalCounts.setTotalColdJarOrdered(totalColdJarOrdered);
          totalCounts.setTotalColdJarReturnedEmpty(totalColdJarReturned);
          totalCounts.setTotalColdJarReturnedFilled(totalColdJarFilledReturned);
          
          totalCounts.setTotalContainerOrdered(totalContainerOrdered);
          totalCounts.setTotalContainerReturned(totalContainerReturned);
          
          totalCounts.setTotalPayment(totalPayment);
          
          
          }catch(Exception ex){
         	 logger.error("ERROR occured at getOrderDetailsByDate()"+ ex.getMessage());
          }
          logger.debug("END : getOrderDetailsByDate()  "+ new Date());
		
		return totalCounts;
	}

	
	public void setDefaulterService(DefaultersService defaulterService) {
		this.defaulterService = defaulterService;
	}
	
	
}
