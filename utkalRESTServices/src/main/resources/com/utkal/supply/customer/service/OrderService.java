package com.utkal.supply.customer.service;

import java.util.List;

import com.utkal.supply.model.Order;
import com.utkal.supply.model.OrderHistory;
import com.utkal.supply.model.TotalCounts;

public interface OrderService {
	
	public int createOrder(Order order) throws Exception;
	
	public Order getCustomerPaymentDetails(String customerId);
	
	public Order getCustomerOrderByDate(Order order);
	
	public Order updateOrder(Order order) throws Exception;
	
	public List<Order> getOrderHistory(OrderHistory orderHistory);
	
	public TotalCounts getOrderDetailsByDate(OrderHistory orderHistory);

}
