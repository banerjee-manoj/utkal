package com.utkal.supply.controller;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.utkal.supply.model.User;
import com.utkal.supply.service.user.UserService;


@Component
@Path("/secured/login")
public class LoginController {

	
	public static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public User login(User user, @Context HttpServletResponse response){
		
		logger.info("BEGIN : Login() method...");
		userService.validateUser(user);
		
		//if(user.getUserName().equals("Admin") && user.getPassword().equals("Admin")){
			
			response.setHeader("result","success");
		//}
		
		logger.info("END : Login() method...");
		return user;
	}
	
}
