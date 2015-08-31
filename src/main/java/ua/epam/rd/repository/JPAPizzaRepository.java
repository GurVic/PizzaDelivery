package ua.epam.rd.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;

@Repository("pizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public List<Pizza> getAllPizzas() {
        TypedQuery<Pizza> query
                = em.createQuery("select p from Pizza p", Pizza.class);
        return query.getResultList();
    }

    @Override
    public List<Pizza> getPizzasByType(PizzaType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public Long save(Pizza pizza) {
        em.persist(pizza);
        return pizza.getId();
    }

    @Transactional
    @Override
    public Long update(Pizza pizza) {
        em.merge(pizza);
        return pizza.getId();
    }

    @Override
    public Pizza getPizzaById(Long id) {
        System.out.println(" pizza repository : id = " + id);
        return em.find(Pizza.class, id);
    }

}
