package gaurang.singhal.perpule.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cutomer")
public class DbInfo {



	int id;
	String name;
	String address;
	int age;
	String password;

	public DbInfo() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	public String getPassword() {
		return password;
	}


	@Override
	public String toString() {
		return "DbInfo [id=" + id + ", name=" + name + ", address=" + address + ", age=" + age + ", password="
				+ password + "]";
	}


	public void setPassword(String password) {
		this.password = password;
	}



}
