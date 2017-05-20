package com.utkal.supply.customer.service;

import com.utkal.supply.dao.OrderDao;
import com.utkal.supply.dao.OrderDaoImpl;
import com.utkal.supply.model.Order;
import com.utkal.supply.utils.UtkalApplicationUtility;

public class OrderServiceImpl implements OrderService{

	@Override
	public String createOrder(Order order) throws Exception {

	      OrderDao orderDao = new OrderDaoImpl();
	      //correct date format to be inserted
	     order.setOrderDate(UtkalApplicationUtility.getFormattedDate(order.getOrderDate()));
	      //order.setNormalWaterJarPending(order.getNormalWaterJarOrder()-)
	      orderDao.createOrder(order);
	      orderDao.makePayment(order);
		
		return "yes";
	}

	@Override
	public Order getCustomerPaymentDetails(String customerId) {
       Order order= new Order();
		OrderDao orderDao= new OrderDaoImpl();
		order = orderDao.getOrderDetailsByCustomerId(customerId);
		order.setCustomerId(customerId);
		return orderDao.getPrevJarOrderDetailsByCustomerId(order);
		
	}

}
