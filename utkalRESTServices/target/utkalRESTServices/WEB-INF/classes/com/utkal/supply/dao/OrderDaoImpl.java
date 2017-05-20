package com.utkal.supply.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utkal.supply.model.Order;

public class OrderDaoImpl implements OrderDao {

	Connection sqlConnection;

	
	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.utkal.supply.dao.OrderDao#createOrder(com.utkal.supply.model.Order)
	 */
	
	@Override
	public String createOrder(Order order) {
		try{
            sqlConnection = getConnection();
            Statement stmt=sqlConnection.createStatement(); 
           String query ="insert into jar(customer_id,transaction_dt,normal_jar_taken,cold_jar_taken,normal_jar_return_filled,normal_jar_return_empty,cold_jar_return_filled,cold_jar_return_empty,normal_jar_pending,cold_jar_pending,container_pending) values("+order.getCustomerId()+",'"+order.getOrderDate()+"',"+order.getNormalWaterJarOrder()+","+order.getColdWaterJarOrder()+","+order.getNormalWaterJarReturnedFilled()+","+order.getNormalWaterJarReturnedEmpty()+ ","+order.getColdWaterJarReturnedFilled()+","+order.getColWaterJarReturnedEmpty()+","+order.getNormalWaterJarPending()+","+order.getColdWaterJarPending()+","+order.getContainerPending()+")";
           System.out.println("Order Insert Query is "+ query); 
           boolean rs=stmt.execute(query);
            
            System.out.println("Resutll is rs "+rs);
		}catch(Exception ex){
			System.out.println("Exception is "+ ex.getMessage());
		}finally {
			try{
				sqlConnection.close();
			}catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());}
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



     /*
      * 
      * 
      * (non-Javadoc)
      * @see com.utkal.supply.dao.OrderDao#makePayment(com.utkal.supply.model.Order)
      */

	@Override
	public String makePayment(Order order) {
		try{
            sqlConnection = getConnection();
            Statement stmt=sqlConnection.createStatement(); 
           String query ="insert into payment(customer_id,transaction_dt,total_bill,payment_rcvd,payment_due) values("+order.getCustomerId()+",NOW(),"+order.getTotalBill()+","+order.getPaymentRcvd()+","+order.getOutstandingAmmount()+")";
           System.out.println("Order Insert Query is "+ query); 
           boolean rs=stmt.execute(query);
            
            System.out.println("Resutll is rs "+rs);
		}catch(Exception ex){
			System.out.println("Exception is "+ ex.getMessage());
		}finally {
			try{
				sqlConnection.close();
			}catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());}
		}

            return "yes";
	}


public static void main(String args[]){
	
	OrderDaoImpl dao = new OrderDaoImpl();
	dao.getOrderDetailsByCustomerId("3");
}


	@Override
	public  Order getOrderDetailsByCustomerId(String customerId) {
		
		Order order = new Order();
		try{
            sqlConnection = getConnection();
            Statement stmt=sqlConnection.createStatement(); 
           String query ="select * from payment where transaction_dt IN (SELECT MAX(transaction_dt) from payment where customer_id="+customerId+") and customer_id="+customerId;
           System.out.println("Order search Query is "+ query); 
           ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
            	order.setPreviousDue(Integer.parseInt(rs.getString("payment_due")));
            	order.setCustomerId(rs.getString("customer_id"));
            	
            }
		}catch(Exception ex){
			System.out.println("Exception is "+ ex.getMessage());
		}finally {
			try{
				sqlConnection.close();
			}catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());}
		}

            return order;
	}





	@Override
	public Order getPrevJarOrderDetailsByCustomerId(Order order) {
		String customerId = order.getCustomerId();
		try{
            sqlConnection = getConnection();
            Statement stmt=sqlConnection.createStatement(); 
           String query ="select * from jar where transaction_dt IN (SELECT MAX(transaction_dt) from jar where customer_id="+customerId+") and customer_id="+customerId;
           System.out.println("Order search Query is "+ query); 
           ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
            	order.setNormalWaterJarPending(Integer.parseInt(rs.getString("normal_jar_pending")));
            	order.setColdWaterJarPending(Integer.parseInt(rs.getString("cold_jar_pending")));
            	order.setContainerPending(Integer.parseInt(rs.getString("container_pending")));
            	
            }
		}catch(Exception ex){
			System.out.println("Exception is "+ ex.getMessage());
		}finally {
			try{
				sqlConnection.close();
			}catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());}
		}

            return order;
	}

	

	
	
	
	
	
	
	

}
