package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.service.CustomerService;
import ua.epam.rd.service.OrderService;
import ua.epam.rd.service.PizzaService;

import java.beans.PropertyEditorSupport;

public abstract class AbstractCartContoller {

    @Autowired
    protected Cart userCart;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected PizzaService pizzaService;
    
    private Pizza getPizzaById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id < 0");
        }
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) {
            throw new NotFoundPizzaException("Pizza id" + id + " not found");
        }
        return pizza;
    }

    @InitBinder
    protected void pizzaBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String pizzaId) {
                        Pizza pizza = null;
                        if (pizzaId != null && !pizzaId.trim().isEmpty()) {
                            Long id = Long.valueOf(pizzaId);
                            pizza = getPizzaById(id);
                        }
                        setValue(pizza);
                    }
                }
        );
    }
}
