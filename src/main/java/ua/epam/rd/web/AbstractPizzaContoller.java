/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.service.PizzaService;

import java.beans.PropertyEditorSupport;

public abstract class AbstractPizzaContoller {

    @Autowired
    protected PizzaService pizzaService;

    @Autowired
    protected Cart userCart;

    protected Pizza getPizzaById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id < 0");
        }
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) {
            throw new NotFoundPizzaException("Pizza id " + id + " not found");
        }
        return pizza;
    }

    @InitBinder
    private void pizzaBinder(WebDataBinder binder) {
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
