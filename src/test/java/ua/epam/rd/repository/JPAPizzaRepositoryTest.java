/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.repository;

import ua.epam.rd.repository.template.ITRepositoryTestsTemplate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;

public class JPAPizzaRepositoryTest extends ITRepositoryTestsTemplate {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void testSomeMethod() {
        Pizza pizza = new Pizza();
        pizza.setName("Meet");
        pizza.setType(PizzaType.Meat);
        pizza.setPrice(123.1);

        Long id = pizzaRepository.save(pizza);
        System.out.println(id);

        assertNotNull(id);
        //fail("The test case is a prototype.");
    }

}
