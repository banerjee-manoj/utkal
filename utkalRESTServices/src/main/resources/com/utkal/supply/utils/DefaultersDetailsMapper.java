package com.utkal.supply.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.utkal.supply.model.PreviousPendingDetails;

public class DefaultersDetailsMapper implements RowMapper<PreviousPendingDetails> {

	@Override
	public PreviousPendingDetails mapRow(ResultSet rs, int arg1)
			throws SQLException {
		
		PreviousPendingDetails prevDetails = new PreviousPendingDetails();
		prevDetails.setCustomerId(rs.getString("customer_id"));
		prevDetails.setCustomerName(rs.getString("customer_name"));
		prevDetails.setPrevNormalJarPending(rs.getString("normal_jar_pending"));
		prevDetails.setPrevColdJarPending(rs.getString("cold_jar_pending"));
		prevDetails.setPrevContainerPending(rs.getString("container_pending"));
		prevDetails.setPrevPaymentDue(rs.getString("payment_due"));
		
		
		return prevDetails;
	}

}
