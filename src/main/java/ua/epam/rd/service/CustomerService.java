package ua.epam.rd.service;


import ua.epam.rd.domain.Customer;

public interface CustomerService {
	Customer findCustomerById(Long id);
	Customer findCustomerByName(String name);
	Long save(Customer customer);
}
