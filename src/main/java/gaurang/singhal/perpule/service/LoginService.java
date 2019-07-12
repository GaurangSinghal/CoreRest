package gaurang.singhal.perpule.service;

import gaurang.singhal.perpule.model.Credentials;
import gaurang.singhal.perpule.repository.LoginRepository;

public class LoginService {

	LoginRepository rep= new LoginRepository();
	
	public String authenticate(Credentials credentials) {
		if(!rep.authenticate(credentials))
			return null;
		String token= generateToken(credentials);
		rep.addToken(token, credentials.getId());
		return token;
	}
	
	public String generateToken(Credentials credentials) {
	    return credentials.getId()+"|"+credentials.getPassword();
	}

}
