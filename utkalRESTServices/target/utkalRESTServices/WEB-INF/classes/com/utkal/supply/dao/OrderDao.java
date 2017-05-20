package com.utkal.supply.dao;

import com.utkal.supply.model.Order;

public interface OrderDao {
	
	public String createOrder(Order order);
	
	public String makePayment(Order order);
	
	public Order getOrderDetailsByCustomerId(String customerId);
	
	public Order getPrevJarOrderDetailsByCustomerId(Order order);

}
