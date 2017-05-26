package com.utkal.supply.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

//import bsh.org.objectweb.asm.Type;

import com.utkal.supply.model.Customer;
import com.utkal.supply.model.JarDefaulter;
import com.utkal.supply.model.PreviousPendingDetails;
import com.utkal.supply.utils.CustomerListMapper;
import com.utkal.supply.utils.UtkalApplicationUtility;

@Repository
public class CustomerDaoImpl implements CustomerDao{

	public static Logger logger = Logger.getLogger(CustomerDaoImpl.class);
	
	public DataSource dataSource;
	public JdbcTemplate jdbcTemplate;
	/*Connection sqlConnection;*/

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}	
	
	
	@Override
	public int saveCusomer(Customer customer){
            logger.debug("BEGIN : saveCusomer()");	
            int result=0;
		try{
            String query ="insert into customer_details(customer_name,Address,mobile_number,customer_type,security_deposit,normal_jar_rate,cold_jar_rate,activationDate) values('"+customer.getCustomerName()+"','"+customer.getAddress()+"','"+customer.getMobileNumber()+"','"+customer.getCustomerType()+"','"+customer.getSecurityDeposit()+"','"+customer.getNormalJarRate()+"','"+customer.getColdJarRate()+"','"+
            UtkalApplicationUtility.getFormattedDate(customer.getActivationDate())+"')";
            logger.debug(query);
            result =jdbcTemplate.update(query);
		}catch(Exception ex){
			System.out.println("Exception is "+ ex.getMessage());
		}
		 logger.debug("END : saveCusomer()");
         return result;
	}


/*	public Connection getConnection(){
	
		Connection con = null;
		
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/utkal","root","root");  
			}catch(Exception e){
				System.out.println(e);
				}  
			
	return con;
	}*/

		

	@Override
	public Map<String,String> getCustomerList() {
		
		logger.debug("BEGIN : getCustomerList() method");
		final Map<String,String> customerList = new HashMap<String,String>();
    	String query ="select customer_id,customer_name from customer_details";
		try{
            logger.debug("Query to be Executed : "+ query);
            jdbcTemplate.query(query, new ResultSetExtractor<Object>(){
				@Override
				public Object extractData(ResultSet resultSet) throws SQLException,
						DataAccessException {
					while(resultSet.next()){
		            	customerList.put(resultSet.getString(1),resultSet.getString(2));
		            }
					return resultSet;
				}
            });
            
		}catch(Exception ex){
			logger.error("ERROR : Error in  getCustomerList() : "+ ex.getMessage());
		}
		logger.debug("END : getCustomerList() method, return result size is "+ customerList.size());
		return customerList;
	}


   /**
    * 
    * 
    * 
    */
	@Override
	public Customer getCustomerDetails(String customerId)  {
		logger.debug("BEGIN : getCustomerDetails()");
		final Customer customer = new Customer();
		try{
            String query ="select * from customer_details where customer_id="+customerId+"";
            logger.debug("Query to be executed :"+query); 

            jdbcTemplate.query(query, new ResultSetExtractor<Object>(){
					@Override
					public Object extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						while(rs.next()){
			            	customer.setCustomerName(rs.getString(2));
			            	customer.setCustomerType(rs.getString(5));
			            	customer.setAddress(rs.getString(3));
			            	customer.setMobileNumber(rs.getString(4));
			            	customer.setSecurityDeposit(rs.getString(6));
			            	customer.setCustomerId(rs.getString(1));
			            	customer.setNormalJarRate(Integer.parseInt(rs.getString(8)));
			            	customer.setColdJarRate(Integer.parseInt(rs.getString(9)));
			            	try {
			            		if(null != rs.getString("activationDate")){
								customer.setActivationDate(UtkalApplicationUtility.getUIFormattedDate(rs.getString("activationDate")));
			            		}
							} catch (Exception e) {
								e.printStackTrace();
							}
			            }
						return rs;
					}});
		}catch(Exception ex){
			logger.error("ERROR : Error Occured at getCustomerDetails()  is "+ex.getMessage());
		}
	   logger.debug("END : getCustomerDetails()");
		return customer;
	}



	@Override
	public int updateCustomer(Customer customer) {
		logger.debug("BEGIN : updateCustomer()");
		int result =0;
		try{
           // sqlConnection = getConnection();
            //Statement stmt=sqlConnection.createStatement(); 
            String query ="update customer_details set customer_name='"+customer.getCustomerName()+"',address='"+customer.getAddress()+"',mobile_number='"+customer.getMobileNumber()+"',customer_type='"+customer.getCustomerType()+"',security_deposit='"+customer.getSecurityDeposit()+"'," +
            		" normal_jar_rate="+customer.getNormalJarRate()+",cold_jar_rate="+customer.getColdJarRate()+", activationDate='"+UtkalApplicationUtility.getFormattedDateWithoutModification(customer.getActivationDate())+"' where customer_id="+customer.getCustomerId();
            logger.debug("Query to be executed is "+query);
            //boolean rs=stmt.execute(query);
           result = jdbcTemplate.update(query);
            //System.out.println("Resutll is rs "+rs);
		}catch(Exception ex){
			logger.error("ERROR : Error occured at updateCustomer()  is "+ ex.getMessage());
		}
	     logger.debug("END : updateCustomer()");
		return result;
	}


	@Override
	public List<Customer> getCustomerListJson() {

		
		logger.debug("BEGIN : getCustomerListJson() method");
		List<Customer> customerList = new ArrayList<Customer>();
    	String query ="select *  from customer_details";
		try{
            logger.debug("Query to be Executed : "+ query);
            
         customerList= jdbcTemplate.query(query,new CustomerListMapper());
            
		}catch(Exception ex){
			logger.error("ERROR : Error in  getCustomerListJson() : "+ ex.getMessage());
		}
		logger.debug("END : getCustomerListJson() method, return result size is "+ customerList.size());
		return customerList;
	
	}


	@Override
	public PreviousPendingDetails checkForDelete(String customerId) {

		final PreviousPendingDetails pendingDetails = new PreviousPendingDetails();
		logger.debug("START : checkForDelete()");
		String query = "select * from pending where customer_id="+customerId;
		
		jdbcTemplate.query(query, new ResultSetExtractor<Object>(){

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				while(rs.next()){
					pendingDetails.setCustomerId(rs.getString("customer_id"));
					pendingDetails.setCustomerName(rs.getString("customer_name"));
					pendingDetails.setPrevColdJarPending(rs.getString("cold_jar_pending"));
					pendingDetails.setPrevContainerPending(rs.getString("container_pending"));
					pendingDetails.setPrevNormalJarPending(rs.getString("normal_jar_pending"));
					pendingDetails.setPrevPaymentDue(rs.getString("payment_due"));
				}
				
				return rs;
			}
			
		});
		
		logger.debug("END : checkForDelete()");
		return pendingDetails;
	}


	@Override
	public String deleteCustomerById(String customerId) {
                String result = "0";
		             logger.debug("START :deleteCustomerById()");
		String query = "delete from customer_details where customer_id= ?";
	int row =jdbcTemplate.update(query,customerId);
	 System.out.println(row+"  deleted.");
	 if(row >0){
		 int jarDeleteRows = jdbcTemplate.update("delete from jar where customer_id=?",customerId);
		 System.out.println("Jar deleted rows "+ jarDeleteRows);
		 result = "1";
		   if(jarDeleteRows >0){
			   
			     int pendingDetailsRows = jdbcTemplate.update("delete from pending where customer_id=?",customerId);
			     System.out.println("Pending Details rows "+ pendingDetailsRows);
			     result = "2";
		   }
	 }
		             
		             logger.debug("END :deleteCustomerById()");
		
		
		
		return result;
	}
	
		
		
		
	
	
	
	
}