package com.utkal.supply.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.utkal.supply.model.JarDefaulter;
import com.utkal.supply.model.PaymentDefaulters;
import com.utkal.supply.utils.JarDefaulterMapper;
import com.utkal.supply.utils.MoneyDefaulterMapper;

@Repository
public class DefaultersDaoImpl implements DefaultersDao {
	public Logger logger = Logger.getLogger(DefaultersDaoImpl.class);

	public DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public Connection conn;
	private PaymentDefaulters paymentDefaulters;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public List<PaymentDefaulters> getPaymentDefaulters() {
		logger.debug("BEGIN : getPaymentDefaulters()");
		List<PaymentDefaulters> paymentDefaultersList = new ArrayList<PaymentDefaulters>();
		try{
			String query = "select custDtls.customer_id,custDtls.customer_name,custDtls.mobile_number, payment.payment_due from  customer_details custDtls, payment payment "+
            "where custDtls.customer_id=payment.customer_id and "+ 
            "payment.transaction_dt IN (SELECT MAX(payment.transaction_dt) from payment where custDtls.customer_id=payment.customer_id) "+ 
            "and custDtls.customer_id=payment.customer_id and payment.payment_due>0";

		paymentDefaultersList = jdbcTemplate.query(query, new MoneyDefaulterMapper());
		}catch (Exception e) {
			logger.error("ERROR : Error Occured during execution of getPaymentDefaulters() method.. "+e.getMessage());
		}
		logger.debug("END : getPaymentDefaulters(), size of the paymentdefaulters List "+ paymentDefaultersList.size());
		return paymentDefaultersList;
	}


	@Override
	public List<JarDefaulter> getJarDefaulters() {
		logger.debug("BEGIN : getJarDefaulters()");
		List<JarDefaulter> jarDefaultersList = new ArrayList<JarDefaulter>();
		try{
			String query = "select custDtls.customer_id,custDtls.customer_name,custDtls.mobile_number, jar.normal_jar_pending,jar.cold_jar_pending from "+  
			"customer_details custDtls, jar jar "+
			"where custDtls.customer_id=jar.customer_id and "+  
			"jar.transaction_dt IN (SELECT MAX(jar.transaction_dt) from jar where custDtls.customer_id=jar.customer_id)"+  
			" and custDtls.customer_id=jar.customer_id and (jar.normal_jar_pending>0 or jar.cold_jar_pending>0)";

			jarDefaultersList = jdbcTemplate.query(query, new JarDefaulterMapper());
		}catch (Exception e) {
			logger.error("ERROR : Error Occured during execution of getJarDefaulters() method.. "+e.getMessage());
		}
		logger.debug("END : getJarDefaulters(), size of the getJarDefaulters List "+ jarDefaultersList.size());
		return jarDefaultersList;
	}

}
