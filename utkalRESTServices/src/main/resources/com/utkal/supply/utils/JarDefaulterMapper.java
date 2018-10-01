package com.utkal.supply.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.utkal.supply.model.JarDefaulter;

public class JarDefaulterMapper implements RowMapper<JarDefaulter> {

	@Override
	public JarDefaulter mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		JarDefaulter jarDefaulters = new JarDefaulter();
		jarDefaulters.setCustomerId(Integer.parseInt(rs.getString(1)));
		jarDefaulters.setCustomerName(rs.getString(2));
		jarDefaulters.setMobileNumber(rs.getString(3));
		jarDefaulters.setNormalJar(rs.getString(4));
		jarDefaulters.setColdJar(rs.getString(5));
		
		return jarDefaulters;
	}

}
