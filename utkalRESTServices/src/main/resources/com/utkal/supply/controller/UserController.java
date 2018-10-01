package com.utkal.supply.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.utkal.supply.model.User;
import com.utkal.supply.service.user.UserService;


@Component
@Path("/secured/user")
public class UserController {
	
	
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/createUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user){
		logger.debug("BEGIN:  This is the userController()");
		System.out.println(user.getPassword());
		System.out.println(user.getUserName());
		try{
		userService.createUser(user);
		
		}catch(Exception ex){
			logger.error("Error occured here .. "+ex.getMessage());
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		logger.debug("END:  This is the userController()");
		return user;
	}
	

}

