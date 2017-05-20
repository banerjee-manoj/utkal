package com.utkal.supply.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.utkal.supply.model.PaymentDefaulters;

public class MoneyDefaulterMapper implements RowMapper<PaymentDefaulters> {

	@Override
	public PaymentDefaulters mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		PaymentDefaulters paymentDefaulters = new PaymentDefaulters();
		paymentDefaulters.setCustomerId(Integer.parseInt(rs.getString(1)));
		paymentDefaulters.setCustomerName(rs.getString(2));
		paymentDefaulters.setMobileNumber(rs.getString(3));
		paymentDefaulters.setPaymentDue(rs.getString(4));
		
		return paymentDefaulters;
	}

}
