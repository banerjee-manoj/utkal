/**
 * 
 */
package com.utkal.supply.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.utkal.supply.model.User;
import com.utkal.supply.service.user.UserService;
import com.utkal.supply.utils.UtkalApplicationUtility;

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
							}
							return rs;
						}
		      		});
		      	 }catch(Exception ex){
		      		 logger.error(ex.getMessage());}
		          logger.debug("END :  validateUser() ");
	}

}
