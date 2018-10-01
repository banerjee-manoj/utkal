package com.utkal.supply.interceptors;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.testng.log4testng.Logger;

import com.utkal.supply.jwt.utils.JWTTokenCreator;
import com.utkal.supply.jwt.utils.PrivateKeySingleTon;


@Provider
public class RequestInterceptor implements ContainerRequestFilter {

	private Logger logger = Logger.getLogger(RequestInterceptor.class);
	public static final String AUTHENTICATION_HEADER = "AuthToken";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains("secured/")){
			String token = requestContext.getHeaderString(AUTHENTICATION_HEADER);
			if(null != token){
			JWTTokenCreator.parseJWT(token);
			//token is not null. check for the expiration time of the auth token and also check the signing parameters.
			// if all looks good then propagate to the next level.
			
			
			}else {
				 throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}else if(requestContext.getUriInfo().getPath().contains("login/")){
		 logger.debug("Login URI do not do anything here...");	
		}else{
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		
	}

}
