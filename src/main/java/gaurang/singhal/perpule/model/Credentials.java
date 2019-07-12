package gaurang.singhal.perpule.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "credential")
public class Credentials {
	int id;
	String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Credentials [id=" + id + ", password=" + password + "]";
	}

}
