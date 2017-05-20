package com.utkal.supply.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.utkal.supply.customer.service.CustomerServiceImpl;
import com.utkal.supply.model.Customer;

public class CustomerDaoImpl implements CustomerDao{

	public static Logger logger = Logger.getLogger(CustomerDaoImpl.class);
	
	public DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	Connection sqlConnection;
	@Override
	public String saveCusomer(Customer customer){
		
		try{
            sqlConnection = getConnection();
            Statement stmt=sqlConnection.createStatement(); 
            String query ="insert into customer_details(customer_name,Address,mobile_number,customer_type,security_deposit,container,normal_jar_rate,cold_jar_rate) values('"+customer.getCustomerName()+"','"+customer.getAddress()+"','"+customer.getMobileNumber()+"','"+customer.getCustomerType()+"','"+customer.getSecurityDeposit()+"','"+customer.getIsContainerRequired()+"','"+customer.getNormalJarRate()+"','"+customer.getColdJarRate()+"')";
            System.out.println(query);
            boolean rs=stmt.execute(query);
            System.out.println("Resutll is rs "+rs);
		}catch(Exception ex){
			System.out.println("Exception is "+ ex.getMessage());
		}

            return "yes";
	}



	public Connection getConnection(){
	
		Connection con = null;
		
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/utkal","root","root");  
			}catch(Exception e){
				System.out.println(e);
				}  
			
	return con;
	}



	@Override
	public Map<String,String> getCustomerList() {
		Map<String,String> customerList = new HashMap<String,String>();
		//Customer customer=null;
		try{
			sqlConnection = getConnection();
            System.out.println("IN the customer save dao impl method");
            Statement stmt=sqlConnection.createStatement(); 
            String query ="select customer_id,customer_name from customer_details";
            System.out.println(query); 
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
            	System.out.println("get Nme "+ rs.getString(1));
            	customerList.put(rs.getString(1),rs.getString(2));
            }
		}catch(Exception ex){
			
			System.out.println("Exception is "+ex.getMessage());
		}
		
		return customerList;
	}



	@Override
	public Customer getCustomerDetails(String customerId) {
		logger.debug("BEGIN : getCustomerDetails()");
		Customer customer = new Customer();
		try{
			sqlConnection = getConnection();
            Statement stmt=sqlConnection.createStatement(); 
            String query ="select * from customer_details where customer_id="+customerId+"";
            System.out.println(query); 
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
            	customer.setCustomerName(rs.getString(2));
            	customer.setCustomerType(rs.getString(5));
            	customer.setAddress(rs.getString(3));
            	customer.setMobileNumber(rs.getString(4));
            	customer.setSecurityDeposit(rs.getString(6));
            	customer.setCustomerId(rs.getString(1));
            	customer.setNormalJarRate(Integer.parseInt(rs.getString(8)));
            	customer.setColdJarRate(Integer.parseInt(rs.getString(9)));
            }
		}catch(Exception ex){
			
			logger.error("Exception is "+ex.getMessage());
		}
		
		return customer;
	}



	@Override
	public String updateCustomer(Customer customer) {
		try{
            sqlConnection = getConnection();
            Statement stmt=sqlConnection.createStatement(); 
            String query ="update customer_details set customer_name='"+customer.getCustomerName()+"',address='"+customer.getAddress()+"',mobile_number='"+customer.getMobileNumber()+"',customer_type='"+customer.getCustomerType()+"',security_deposit='"+customer.getSecurityDeposit()+"'," +
            		" normal_jar_rate="+customer.getNormalJarRate()+",cold_jar_rate="+customer.getColdJarRate()+" where customer_id="+customer.getCustomerId();
            System.out.println(query);
            boolean rs=stmt.execute(query);
            System.out.println("Resutll is rs "+rs);
		}catch(Exception ex){
			System.out.println("Exception is "+ ex.getMessage());
		}
		
		return null;
	}
	
		
		
		
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}	
	
	
}