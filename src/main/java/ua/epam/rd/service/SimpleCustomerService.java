package ua.epam.rd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.rd.domain.Customer;
import ua.epam.rd.repository.CustomerRepository;

@Service("customerService")
public class SimpleCustomerService implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findCustomerById(Long id) {
		return customerRepository.findCustomerById(id);
	}
	
	public Customer findCustomerByName(String name) {
		return customerRepository.findCustomerByName(name);
	}

	public Long save(Customer customer) {
		return customerRepository.save(customer);
	}

}
