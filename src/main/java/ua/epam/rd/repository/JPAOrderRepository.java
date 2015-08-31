package ua.epam.rd.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ua.epam.rd.domain.Order;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext(name = "HibernateMySQL") //, type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Long getNewOrderID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        TypedQuery<Order> query = 
                em.createQuery("select o from Order o", Order.class);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long id) {
        TypedQuery<Order> query = em.createNamedQuery("getOrdersByCustomerId", Order.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override    
    public Order getOrderById(Long id) {
        Order order = em.find(Order.class, id);
        return order;
    }

    @Override
    public Long save(Order order) {
        em.persist(order);
        return order.getOrderId();
    }

    @Override
    public Long update(Order order) {
        em.merge(order);
        return order.getOrderId();
    }

}
