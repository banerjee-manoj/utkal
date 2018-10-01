package com.utkal.supply.controller;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.utkal.supply.jwt.utils.JWTTokenCreator;
import com.utkal.supply.jwt.utils.PrivateKeySingleTon;
import com.utkal.supply.model.User;
import com.utkal.supply.service.user.UserService;


@Component
@Path("/login")
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
		
		String token = null;
		
		userService.validateUser(user);
		if(user.isValidUser()){
		//token =JWTTokenCreator.getToken(PrivateKeySingleTon.getInstance().getPrivateKeyString());
		token = JWTTokenCreator.createToken();
		response.setHeader("result","success");
		response.setHeader("AuthToken", token);
		}else{
			user.setPassword("");
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		
		logger.info("END : Login() method...");
		user.setPassword("");
		return user;
	}
	
}
