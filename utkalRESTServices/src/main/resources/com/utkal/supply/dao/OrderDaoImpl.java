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

import com.utkal.supply.model.Order;
import com.utkal.supply.model.OrderHistory;
import com.utkal.supply.utils.JarDefaulterMapper;
import com.utkal.supply.utils.OrderHistoryMapper;
import com.utkal.supply.utils.UtkalApplicationUtility;

public class OrderDaoImpl implements OrderDao {

	Connection sqlConnection;
	
	public static Logger logger = Logger.getLogger(OrderDaoImpl.class);
	
	public DataSource dataSource;
	public JdbcTemplate jdbcTemplate;

	
	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.utkal.supply.dao.OrderDao#createOrder(com.utkal.supply.model.Order)
	 */
	
	@Override
	public int createOrder(Order order) {
		logger.debug("BEGIN  : createOrder() method");
		int result =0;
		try{
           String query ="insert into jar(customer_id,transaction_dt,normal_jar_taken,cold_jar_taken,normal_jar_return_filled,normal_jar_return_empty,cold_jar_return_filled,cold_jar_return_empty,normal_jar_pending,cold_jar_pending,container_pending) values("+order.getCustomerId()+",'"+order.getOrderDate()+"',"+order.getNormalWaterJarOrder()+","+order.getColdWaterJarOrder()+","+order.getNormalWaterJarReturnedFilled()+","+order.getNormalWaterJarReturnedEmpty()+ ","+order.getColdWaterJarReturnedFilled()+","+order.getColdWaterJarReturnedEmpty()+","+order.getNormalWaterJarPending()+","+order.getColdWaterJarPending()+","+order.getContainerPending()+")";
           logger.debug("Order Creation  Query is :-->"+ query); 
           result = jdbcTemplate.update(query);            
          logger.debug("createOrder() : order creation is done. Result is "+ result);
		}catch(Exception ex){
			logger.error("ERROR : Error Occured at createOrder Method : "+ ex.getMessage());
		}
		logger.debug("END  : createOrder() method");
            return result;
	}
	

     /*
      * 
      * 
      * (non-Javadoc)
      * @see com.utkal.supply.dao.OrderDao#makePayment(com.utkal.supply.model.Order)
      */

	@Override
	public String makePayment(Order order) {
		logger.debug("BEGIN : makePayment()");
		int result =0;
		try{
           String query ="insert into payment(customer_id,transaction_dt,total_bill,payment_rcvd,payment_due) values("+order.getCustomerId()+",NOW(),"+order.getTotalBill()+","+order.getPaymentRcvd()+","+order.getOutstandingAmmount()+")";
           logger.debug("Query to be makePayment method :--> "+query); 
           result = jdbcTemplate.update(query);
           logger.debug("Payment Result is : "+result);
		}catch(Exception ex){
			logger.error("ERROR : Error Occured at makePayment method : "+ ex.getMessage());
		}
		logger.debug("END : makePayment()");
            return "yes";
	}


     /**
      * 
      * 
      * 
      */
	@Override
	public  Order getOrderDetailsByCustomerId(String customerId) {
		logger.debug("BEGIN : getOrderDetailsByCustomerId()");
		final Order order = new Order();
		try{
           String query ="select * from payment where transaction_dt IN (SELECT MAX(transaction_dt) from payment where customer_id="+customerId+") and customer_id="+customerId;
           logger.debug("Query to get Order Details by Customer ID :--> "+ query); 
           jdbcTemplate.query(query,new ResultSetExtractor<Object>(){
				@Override
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					 while(rs.next()){
			            	order.setPreviousDue(Integer.parseInt(rs.getString("payment_due")));
			            	order.setCustomerId(rs.getString("customer_id"));
			            }
					return rs;
				}});

		}catch(Exception ex){
			logger.error("ERROR : Error Occured at the getOrderDetailsByCustomerId Method : "+ ex.getMessage());
		}
		logger.debug("END : getOrderDetailsByCustomerId()");
		return order;
	}




     /**
      * 
      * 
      * 
      */
	@Override
	public Order getPrevJarOrderDetailsByCustomerId(final Order order) {
        logger.debug("BEGIN : getPrevJarOrderDetailsByCustomerId()");
		String customerId = order.getCustomerId();
		try{
           String query ="select * from jar where transaction_dt IN (SELECT MAX(transaction_dt) from jar where customer_id="+customerId+") and customer_id="+customerId;
           logger.debug("Queyr to get PrevJarOrdeDetails by customer ID :--> "+ query); 
            jdbcTemplate.query(query,new ResultSetExtractor<Object>(){
				@Override
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					 while(rs.next()){
						 order.setNormalWaterJarPending(Integer.parseInt(rs.getString("normal_jar_pending")));
			            	order.setColdWaterJarPending(Integer.parseInt(rs.getString("cold_jar_pending")));
			            	order.setContainerPending(Integer.parseInt(rs.getString("container_pending")));
			            }
					return rs;
				}});
		}catch(Exception ex){
			logger.error("ERROR : Error Occured at getPrevJarOrderDetailsByCustomerId :  "+ ex.getMessage());
		}
		logger.debug("END : getPrevJarOrderDetailsByCustomerId()");
            return order;
	}

	
	
    /**
     * 
     * 	
     */
	
	@Override
	public Order getCustomerOrderByDate(final Order order) {
         logger.debug("BEGIN : getCustomerOrderByDate()");
        int result=0;
         try{
             String query ="select * from jar where customer_id='"+order.getCustomerId()+"' and transaction_dt='"+UtkalApplicationUtility.getFormattedDate(order.getOrderDate())+"'";
             logger.debug("Query to get getCustomerOrderByDate by customer ID and transaction date :--> "+ query); 
              jdbcTemplate.query(query,new ResultSetExtractor<Object>(){
  				@Override
  				public Object extractData(ResultSet rs) throws SQLException,
  						DataAccessException {
  					 while(rs.next()){
  						 logger.debug("When there is a row present for this customer...");
  						   order.setNormalWaterJarOrder(rs.getString("normal_jar_taken"));
  						   order.setColdWaterJarOrder(rs.getString("cold_jar_taken"));
  						   order.setNormalWaterJarReturnedFilled(Integer.parseInt(rs.getString("normal_jar_return_filled")));
  						   order.setNormalWaterJarReturnedEmpty(rs.getString("normal_jar_return_empty"));
  						   order.setNormalWaterJarPending(Integer.parseInt(rs.getString("normal_jar_pending")));
  						   order.setColdWaterJarReturnedFilled(Integer.parseInt(rs.getString("cold_jar_return_filled")));
  						   order.setColdWaterJarReturnedEmpty(Integer.parseInt(rs.getString("cold_jar_return_empty")));
  						   
  			            	order.setColdWaterJarPending(Integer.parseInt(rs.getString("cold_jar_pending")));
  			            	order.setContainerReturned(Integer.parseInt(rs.getString("container_returned")));
  			            	order.setContainerOrdered(Integer.parseInt(rs.getString("container_ordered")));
  			            	order.setTotalBill(rs.getString("total_bill"));
  			            	order.setPaymentRcvd(rs.getString("payment_recvd"));
  			                order.setResult(1);  
  			                order.setNewForm(false);
  			                
  					 
  					 }
  					return rs;
  				}});
         }catch(Exception ex){
        	 logger.error("ERROR : Error Occured at getCustomerOrderByDate() "+ ex.getMessage());
        	 order.setResult(0);
         }
         logger.debug("END : getCustomerOrderByDate()");
		return order;
	}

	
	
	
	
	public Order updateOrder(Order order){
		logger.debug("BEGIN : updateOrder()...");
		String query;
		int result=0;
		try{
		
		if(order.isNewForm()){
			logger.debug("Insert Data for the new form");
			query ="insert into jar(customer_id,transaction_dt,normal_jar_taken,cold_jar_taken,normal_jar_return_filled,normal_jar_return_empty," +
					" cold_jar_return_filled,cold_jar_return_empty,container_ordered,container_returned,total_bill,payment_recvd) " +
	  		"values(?,?,?,?,?,?,?,?,?,?,?,?)";
			result = jdbcTemplate.update(query, new Object[]{order.getCustomerId(),UtkalApplicationUtility.getFormattedDate(order.getOrderDate()),order.getNormalWaterJarOrder(),order.getColdWaterJarOrder(),order.getNormalWaterJarReturnedFilled(),order.getNormalWaterJarReturnedEmpty(),
					order.getColdWaterJarReturnedFilled(),order.getColdWaterJarReturnedEmpty(),order.getContainerOrdered(),order.getContainerReturned(),
					order.getTotalBill(),order.getPaymentRcvd()});
		}else {
			logger.debug("Update Data for the Existing form");
			query="update jar set normal_jar_taken=?,cold_jar_taken=?,normal_jar_return_filled=?,normal_jar_return_empty=?,cold_jar_return_filled=?,cold_jar_return_empty=?" +
					", container_ordered=?,container_returned=?,total_bill=?,payment_recvd=? where customer_id=? and transaction_dt=?";
			result = jdbcTemplate.update(query, order.getNormalWaterJarOrder(),order.getColdWaterJarOrder(),order.getNormalWaterJarReturnedFilled(),order.getNormalWaterJarReturnedEmpty(),
					order.getColdWaterJarReturnedFilled(),order.getColdWaterJarReturnedEmpty(), order.getContainerOrdered(),order.getContainerReturned(),
					order.getTotalBill(),order.getPaymentRcvd(),order.getCustomerId(),UtkalApplicationUtility.getFormattedDate(order.getOrderDate()));
		}
		
		}catch (Exception e) {
            logger.error("ERORR : Error Occured at the updateOrder() "+ e.getMessage());
            result=0;
		}
		
		logger.debug("END : updateOrder()...");
		order.setResult(result);
		return order;
	}
	
	
	
	public List<Order> getOrderHistory(final OrderHistory orderHistory){
		
		logger.debug("BEGIN : getOrderHistory()");
		
		List<Order> orderList = new ArrayList<Order>();
		
		try{
		logger.debug("Customer Id "+ orderHistory.getCustomerId());
        String query = "select * from jar where transaction_dt >=? and transaction_dt <=? and customer_id=?";

        orderList = jdbcTemplate.query(query,new Object[]{UtkalApplicationUtility.getFormattedDate(orderHistory.getStartDate()),
				UtkalApplicationUtility.getFormattedDate(orderHistory.getEndDate()),orderHistory.getCustomerId()}, new OrderHistoryMapper());
        
        
        
        /*jdbcTemplate.query(query, new Object[]{UtkalApplicationUtility.getFormattedDate(orderHistory.getStartDate()),
				UtkalApplicationUtility.getFormattedDate(orderHistory.getEndDate()),orderHistory.getCustomerId()} ,new ResultSetExtractor<Object>(){
				@Override
				public Object extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					 while(rs.next()){

					System.out.println( rs.getString("customer_id"));
					orderReturn.setCustomerId(rs.getString("customer_id"));
					orderReturn.setResult(1);
					 }
					return rs;
				}});
		System.out.println("Result Returned is "+ orderReturn.getCustomerId());
		System.out.println("Return Result value is "+ orderReturn.getResult());
*/		}catch(Exception ex){
	logger.error("ERROR  occured at getOrderHistory() "+ ex.getMessage());}
logger.debug("END : getOrderHistory()");
	     return orderList;
	}
      
      
      
	
	/**
	 * 
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	/**
	 * 
	 * 
	 * 
	 */

	@Override
	public List<Order> getOrderDetailsBydate(OrderHistory orderHistory) {
		
		logger.debug("BEGIN : getOrderHistory()");
		
		List<Order> orderList = new ArrayList<Order>();
		
		try{
		logger.debug("Customer Id "+ orderHistory.getCustomerId());
        String query = "select * from jar jar,customer_details customer where customer.customer_id=jar.customer_id and jar.transaction_dt >=? and jar.transaction_dt <=?";

        orderList = jdbcTemplate.query(query,new Object[]{UtkalApplicationUtility.getFormattedDate(orderHistory.getStartDate()),
				UtkalApplicationUtility.getFormattedDate(orderHistory.getEndDate())}, new OrderHistoryMapper());
        
        
     		}catch(Exception ex){
	logger.error("ERROR  occured at getOrderHistory() "+ ex.getMessage());}
logger.debug("END : getOrderHistory()");
	     return orderList;
	}


	

	
	
	
	
	
	
	

}
