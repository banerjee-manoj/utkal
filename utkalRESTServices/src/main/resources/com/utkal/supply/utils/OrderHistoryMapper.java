package com.utkal.supply.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.utkal.supply.model.Order;

public class OrderHistoryMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int arg1) throws SQLException {
		Order order = new Order();
		try{
		order.setCustomerId(rs.getString("customer_id"));
		order.setOrderDate(UtkalApplicationUtility.getUIFormattedDate(rs.getString("transaction_dt")));
		order.setNormalWaterJarOrder(rs.getString("normal_jar_taken"));
		order.setColdWaterJarOrder(rs.getString("cold_jar_taken"));
		order.setTotalBill(rs.getString("total_bill"));
		order.setPaymentRcvd(rs.getString("payment_recvd"));
		
		
		order.setNormalWaterJarReturnedEmpty(rs.getString("normal_jar_return_empty"));
		order.setNormalWaterJarReturnedFilled(Integer.parseInt(rs.getString("normal_jar_return_filled")));
		
		order.setColdWaterJarReturnedEmpty(Integer.parseInt(rs.getString("cold_jar_return_empty")));
		order.setColdWaterJarReturnedFilled(Integer.parseInt(rs.getString("cold_jar_return_filled")));
		
		order.setContainerOrdered(Integer.parseInt(rs.getString("container_ordered")));
		order.setContainerReturned(Integer.parseInt(rs.getString("container_returned")));
		order.setCustomerName(rs.getString("customer_name"));
		
		}catch(Exception ex){System.out.println("error "+ ex.getMessage());}
		
		
		return order;
	}

}
