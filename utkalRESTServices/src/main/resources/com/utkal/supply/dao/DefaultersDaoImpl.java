package com.utkal.supply.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.utkal.supply.model.JarDefaulter;
import com.utkal.supply.model.PaymentDefaulters;
import com.utkal.supply.model.PreviousPendingDetails;
import com.utkal.supply.utils.DefaultersDetailsMapper;
import com.utkal.supply.utils.JarDefaulterMapper;
import com.utkal.supply.utils.MoneyDefaulterMapper;

@Repository
public class DefaultersDaoImpl implements DefaultersDao {
	public Logger logger = Logger.getLogger(DefaultersDaoImpl.class);

	public DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public Connection conn;
	//private PaymentDefaulters paymentDefaulters;
	
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


	@Override
	public PreviousPendingDetails getPrevDtlsByCustomer(String customerId) {
           logger.debug("BEGIN : getPrevDtlsByCustomer()");
         final PreviousPendingDetails prevDtls = new PreviousPendingDetails();
           String query = "select * from pending where customer_id='"+customerId+"'";
           try{
           jdbcTemplate.query(query, new ResultSetExtractor<Object>(){
				@Override
				public Object extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					while(rs.next()){
						prevDtls.setCustomerId(rs.getString("customer_id"));
						prevDtls.setPrevColdJarPending(rs.getString("cold_jar_pending"));
						prevDtls.setPrevNormalJarPending(rs.getString("normal_jar_pending"));
						prevDtls.setPrevContainerPending(rs.getString("container_pending"));
						prevDtls.setPrevPaymentDue(rs.getString("payment_due"));
						prevDtls.setNewForm(false);
						prevDtls.setResult(1);
		            }
					return rs;
				}});
           }catch (Exception e) {
              logger.error("ERROR : Error Occured at getPrevDtlsByCustomer() "+e.getMessage());
           }
           logger.debug("END : getPrevDtlsByCustomer()");
		return prevDtls;
	}


	@Override
	public PreviousPendingDetails savePrevDtls(PreviousPendingDetails prevDtls) {
		logger.debug("BEGIN : savePrevDtls()");
		String query;
		int result=0;
		try{
			if(prevDtls.isNewForm()){
				logger.debug("When the form is new form");
				 query="insert into pending(customer_id,customer_name, normal_jar_pending,cold_jar_pending, container_pending,payment_due) values(?,?,?,?,?,?)";
                 result = jdbcTemplate.update(query,prevDtls.getCustomerId(),prevDtls.getCustomerName(), prevDtls.getPrevNormalJarPending(),prevDtls.getPrevColdJarPending(),prevDtls.getPrevContainerPending(),
        		 prevDtls.getPrevPaymentDue());
			}else{
				logger.debug("When the form is Old form");
				query="update pending set normal_jar_pending=?, cold_jar_pending=?, container_pending=?,payment_due=? where customer_id=?";
	        	result = jdbcTemplate.update(query, prevDtls.getPrevNormalJarPending(),prevDtls.getPrevColdJarPending(),prevDtls.getPrevContainerPending(),
       			prevDtls.getPrevPaymentDue(),prevDtls.getCustomerId());	
			}
		
		}catch(Exception ex){
			logger.error("ERROR : Error occured at savePrevDtls method "+ ex.getMessage());
			
		}
     prevDtls.setResult(result);
		  logger.debug("END : savePrevDtls()");
		return prevDtls;
	}
	
	
	
	
	
	
	
	public List<PreviousPendingDetails> getDefaulterLists(){
		
		logger.debug("BEGIN : getDefaulterLists");
		String query = "select * from jar";
		try{
			
		}catch(Exception ex){
			logger.error("ERROR occured ar getDefaulterLists() .."+ ex.getMessage());
			}
		logger.debug("END : getDefaulterLists");
		
		
		return null;
	}


	@Override
	public List<PreviousPendingDetails> getDefaulterList() {
		logger.debug("BEGIN : getDefaulterList()");
		List<PreviousPendingDetails> defaultersList = new ArrayList<PreviousPendingDetails>();
		try{
			String query = "select * from pending where normal_jar_pending>0 or cold_jar_pending >0 or container_pending> 0 or payment_due>0";
			defaultersList = jdbcTemplate.query(query, new DefaultersDetailsMapper());
		}catch (Exception e) {
			logger.error("ERROR : Error Occured during execution of getJarDefaulters() method.. "+e.getMessage());
		}
		logger.debug("END : getDefaulterList()");
		return defaultersList;
}
	
	
	
	
	
	
	
	
	
	

}
