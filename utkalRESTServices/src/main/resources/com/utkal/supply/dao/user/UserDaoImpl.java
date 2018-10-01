/**
 * 
 */
package com.utkal.supply.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.utkal.supply.jwt.utils.PrivateKeySingleTon;
import com.utkal.supply.model.User;

/**	
 * @author Manoj
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	public static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	/*@Autowired
	@Qualifier(")
	UserService userServiceImpl;*/
	
	/* (non-Javadoc)
	 * @see com.utkal.supply.dao.user.UserDao#validateUser(com.utkal.supply.model.User)
	 */
	@Override
	public void validateUser(final User user) {
          logger.debug("BEGIN :  validateUser() ");
		          try{
		      		String query="select * from users where user_name=? and password=?";
		      		jdbcTemplate.query(query,new Object[]{user.getUserName(),user.getPassword()},new ResultSetExtractor<Object>(){
						@Override
						public Object extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							while(rs.next()){
								user.setValidUser(true);
								 PrivateKeySingleTon.getInstance().setPrivateKeyString(rs.getString("private_key"));
							}
							return rs;
						}
		      		});
		      	 }catch(Exception ex){
		      		 logger.error(ex.getMessage());}
		          logger.debug("END :  validateUser() ");
	}

	
	/**
	 * 
	 * 
	 */
	@Override
	public String createUser(final User user) {
		logger.debug("BEGIN : createUser()..");
		Map<String, String> userNames = new HashMap<String, String>();
		userNames = getUserList();
		      for(String key: userNames.keySet()){
		    	  if(user.getUserName().equalsIgnoreCase(userNames.get(key))){
		    		 throw new WebApplicationException(Status.CONFLICT);
		    	  }
		      }
		try{
      		String query="insert into users(user_name,password) values(?,?)";// * from users where user_name=? and password=?";
      		jdbcTemplate.update(query,new Object[]{user.getUserName(),user.getPassword()});
      		 	 }catch(Exception ex){
      		 logger.error(ex.getMessage());
      		 throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);}
		logger.debug("END : createUser()..");
		return "";
	}



	private Map<String,String> getUserList() {
		
		logger.debug("BEGIN : getUserList() method");
		final Map<String,String> userList = new HashMap<String,String>();
    	String query ="select user_id,user_name from users";
		try{
            logger.debug("Query to be Executed : "+ query);
            jdbcTemplate.query(query, new ResultSetExtractor<Object>(){
				@Override
				public Object extractData(ResultSet resultSet) throws SQLException,
						DataAccessException {
					while(resultSet.next()){
		            	userList.put(resultSet.getString(1),resultSet.getString(2));
		            }
					return resultSet;
				}
            });
            
		}catch(Exception ex){
			logger.error("ERROR : Error in  getCustomerList() : "+ ex.getMessage());
		}
		logger.debug("END : getCustomerList() method, return result size is "+ userList.size());
		return userList;
	}




}

