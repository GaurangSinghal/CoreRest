package gaurang.singhal.perpule.controller;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import gaurang.singhal.perpule.customAnnotation.Secured;
import gaurang.singhal.perpule.model.Customer;
import gaurang.singhal.perpule.model.DbInfo;
import gaurang.singhal.perpule.service.CustomerService;

@Path("/emp")
public class RestController {

	CustomerService customerService = new CustomerService();

	private static final Logger log = Logger.getLogger(LoginController.class.getName());
	
	@Secured
	@GET
	@Path("/getEmp/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("id") int id) {
		System.out.println("id = " + id);
		log.info("id = " + id);
		return customerService.getCustomer(id);
	}

	@POST
	@Path("/addEmp")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCustomer(DbInfo info) {
		System.out.println("inside add method");
		log.info("inside add method");
		boolean response = customerService.addCustomer(info);
		System.out.println("response = "+response);
		log.info("response = "+response);
	}
}