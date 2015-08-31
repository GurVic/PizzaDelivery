package ua.epam.rd.repository;


import org.springframework.stereotype.Repository;
import ua.epam.rd.domain.Customer;
import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository("customerRepository")
public class JPACustomerRepository implements CustomerRepository {

	@PersistenceContext(name = "HibernateMySQL")
	private EntityManager em;
	
	public Customer findCustomerByName(String name) {
		TypedQuery<Customer> query = em.createNamedQuery("findCustomerByName", Customer.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}
	
	public Customer findCustomerById(Long id) {
		return em.find(Customer.class, id);
	}

    @Transactional
	public Long save(Customer customer) {
		em.persist(customer);
        return customer.getId();
	}
}
