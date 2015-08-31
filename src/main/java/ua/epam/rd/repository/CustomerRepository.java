package ua.epam.rd.repository;


import ua.epam.rd.domain.Customer;

public interface CustomerRepository {
	Customer findCustomerByName(String name);
	Customer findCustomerById(Long id);
	Long save(Customer customer);
}
