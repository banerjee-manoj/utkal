package com.utkal.supply.customer.service;

import com.utkal.supply.model.Order;

public interface OrderService {
	
	public String createOrder(Order order) throws Exception;
	
	public Order getCustomerPaymentDetails(String customerId);

}
