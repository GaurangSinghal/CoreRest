package gaurang.singhal.perpule.controller;

import javax.ws.rs.Consumes;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gaurang.singhal.perpule.model.Credentials;
import gaurang.singhal.perpule.model.Customer;
import gaurang.singhal.perpule.service.LoginService;

@Path("")
public class LoginController {

	LoginService loginservice = new LoginService();

	private static final Logger log = Logger.getLogger(LoginController.class.getName());

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String login(Credentials credentials) {
		System.out.println("inside login method");
		log.info("inside login method");
		String token = loginservice.authenticate(credentials);
		if (token == null)
			return "not valid credentials!";
		return token;
	}
}
