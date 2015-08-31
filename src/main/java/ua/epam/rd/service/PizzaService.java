/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.service;

import ua.epam.rd.domain.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> getAllPizzas();
    Long addPizza(Pizza pizza);
    Long updatePizza(Pizza pizza);
    Pizza getPizzaById(Long id);
}
