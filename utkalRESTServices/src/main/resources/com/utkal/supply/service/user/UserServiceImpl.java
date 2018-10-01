package com.utkal.supply.service.user;



import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utkal.supply.dao.user.UserDao;
import com.utkal.supply.model.User;


@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	
	@Override
	public String validateUser(User user) {
      logger.debug("BEGIN : validateUser()");
      
      logger.debug("END : validateUser()")	;
 userDao.validateUser(user);
 return null;
	}


	@Override
	public String createUser(User user){

		logger.debug("BEGIN : createUser()..");
		try{
		userDao.createUser(user);
		}catch(Exception ex){
			logger.error("ERROR occured..... due to "+ ex.getMessage());
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		logger.debug("END : createUser()..");
		return null;
	}

}
