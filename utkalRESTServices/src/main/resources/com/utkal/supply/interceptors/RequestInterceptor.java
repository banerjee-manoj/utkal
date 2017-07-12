package com.utkal.supply.interceptors;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.testng.log4testng.Logger;


@Provider
public class RequestInterceptor implements ContainerRequestFilter {

	private Logger logger = Logger.getLogger(RequestInterceptor.class);
	public static final String AUTHENTICATION_HEADER = "Authorization";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains("secured/")){
			System.out.println(requestContext.getHeaderString(AUTHENTICATION_HEADER));
			logger.debug("Requesting Restricted paths... needs to pass through the authentication system..");
          //  throw new WebApplicationException(Status.UNAUTHORIZED);
		}else if(requestContext.getUriInfo().getPath().contains("login/")){
		 logger.debug("Login URI do not do anything here...");	
		}else{
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		
	}

}
