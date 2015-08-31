/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.repository;

import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;

import java.util.List;

public interface PizzaRepository {

    List<Pizza> getAllPizzas();

    List<Pizza> getPizzasByType(PizzaType type);

    Long save(Pizza pizza);
    Long update(Pizza pizza);

    Pizza getPizzaById(Long id);
}
