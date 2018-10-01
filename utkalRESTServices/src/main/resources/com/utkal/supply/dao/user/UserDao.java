/**
 * 
 */
package com.utkal.supply.dao.user;

import java.sql.SQLException;

import com.utkal.supply.model.User;

/**
 * @author Manoj
 *
 */
public interface UserDao {
	
	public void validateUser(User user);
	
	public String createUser(User user) throws SQLException;

}
