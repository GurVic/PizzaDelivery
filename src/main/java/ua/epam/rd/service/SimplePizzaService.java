/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.repository.PizzaRepository;

import java.util.List;

@Service
public class SimplePizzaService implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.getAllPizzas();
    }

    @Override
    public Long addPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public Long updatePizza(Pizza pizza) {
        return pizzaRepository.update(pizza);
    }

    @Override
    public Pizza getPizzaById(Long id) {
        return pizzaRepository.getPizzaById(id);
    }
    
}
