package gaurang.singhal.perpule.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.Priorities;
import javax.ws.rs.ext.Provider;

import gaurang.singhal.perpule.controller.LoginController;
import gaurang.singhal.perpule.customAnnotation.Secured;
import gaurang.singhal.perpule.model.Credentials;
import gaurang.singhal.perpule.repository.CustomerRepository;
import gaurang.singhal.perpule.repository.LoginRepository;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

//	private static final String REALM = "example";
//	private static final String AUTHENTICATION_SCHEME = "Bearer";

	private static final Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the Authorization header from the request
		String authorizationHeader = requestContext.getHeaders().getFirst("token");
		System.out.println("authorizationHeader " + authorizationHeader);
		log.info("authorizationHeader " + authorizationHeader);
		// Validate the Authorization header
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return;
		}

		// Extract the token from the Authorization header
//		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

		try {

			// Validate the token
			validateToken(authorizationHeader);

		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		return authorizationHeader != null;

	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {
		System.out.println("unauthorized user");
		log.info("unauthorized user");
		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		requestContext.abortWith(
				Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, "").build());
	}

	private void validateToken(String token) throws Exception {
		System.out.println("token = " + token);
		log.info("token = " + token);
		String[] creds = token.split("\\|");
		System.out.println("creds= "+creds[0]+" "+creds[1]);
		log.info("creds= "+creds[0]+" "+creds[1]);
		int id = Integer.parseInt(creds[0]);
		String pass = creds[1];
		LoginRepository rep = new LoginRepository();
		Credentials cred = new Credentials();
		cred.setId(id);
		cred.setPassword(pass);
		if (!rep.authenticate(cred))
			throw new Exception();
		// Check if the token was issued by the server and if it's not expired
		// Throw an Exception if the token is invalid
	}
}