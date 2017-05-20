package com.utkal.supply.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class ApplicationExceptionMapper extends Exception implements ExceptionMapper<ApplicationExceptionMapper> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Response toResponse(ApplicationExceptionMapper arg0) {
		return Response.status(404).entity(arg0.getMessage()).type("text/plain").build();	
	}

}
