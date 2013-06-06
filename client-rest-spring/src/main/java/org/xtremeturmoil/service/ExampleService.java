package org.xtremeturmoil.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.xtremeturmoil.model.Example;
import org.xtremeturmoil.model.User;

@Path("/example")
public class ExampleService {
	
	private Logger logger = Logger.getLogger(getClass());
	private String ADMIN_ROLE = "Admin";
	
	@Context SecurityContext security;

	@Path("/test")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Example test() {
		logger.info("Test service called");
		Example example = new Example("This is a test.");
		return example;
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/me")
	public User me() {
		if(security!=null) {
			String role = security.isUserInRole(ADMIN_ROLE) ? "Administrator" : "Standard";
			return new User(security.getUserPrincipal().getName(),role);
		} else {
			return null;
		}
	}
	

}
