package com.utkal.supply.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.utkal.supply.model.Customer;

public class CustomerListMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
                 Customer customer = new Customer();
		customer.setCustomerId(rs.getString("customer_id"));
		customer.setCustomerName(rs.getString("customer_name"));
		
		customer.setActivationDate(rs.getString("activationDate"));
		
		customer.setMobileNumber(rs.getString("mobile_number"));
		customer.setNormalJarRate(Integer.parseInt(rs.getString("normal_jar_rate")));
		customer.setColdJarRate(Integer.parseInt(rs.getString("cold_jar_rate")));
		
		return customer;
	}

}
