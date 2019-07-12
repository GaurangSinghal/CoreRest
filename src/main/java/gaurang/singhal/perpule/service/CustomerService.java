package gaurang.singhal.perpule.service;

import gaurang.singhal.perpule.model.Customer;
import gaurang.singhal.perpule.model.DbInfo;
import gaurang.singhal.perpule.repository.CustomerRepository;
import gaurang.singhal.perpule.repository.LoginRepository;

public class CustomerService {

	CustomerRepository customerRepository = new CustomerRepository();
	LoginRepository loginRepository =new LoginRepository();

	public boolean addCustomer(DbInfo customer) {
		return customerRepository.addCustomer(customer) && loginRepository.addCredential(customer);
	}
	
	public Customer getCustomer(int id) {
		return customerRepository.getCustomer(id);
	}
	
}
