package com.utkal.supply.dao;

import java.util.List;

import com.utkal.supply.model.Order;
import com.utkal.supply.model.OrderHistory;

public interface OrderDao {
	
	public int createOrder(Order order);
	
	public String makePayment(Order order);
	
	public Order getOrderDetailsByCustomerId(String customerId);
	
	public Order getPrevJarOrderDetailsByCustomerId(Order order);
	
	public Order getCustomerOrderByDate(Order order);
	
	public Order updateOrder(Order order);
	
	public List<Order>  getOrderHistory(OrderHistory orderHistory);
	
	public List<Order>  getOrderDetailsBydate(OrderHistory orderHistory);

}
