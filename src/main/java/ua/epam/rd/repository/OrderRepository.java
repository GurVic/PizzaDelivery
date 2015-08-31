/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.repository;

import ua.epam.rd.domain.Order;

import java.util.List;


public interface OrderRepository {

    Long getNewOrderID();
    
    List<Order> getAllOrders();

    List<Order> getOrdersByCustomerId(Long id);

    Order getOrderById(Long id);
   
    Long save(Order order);

    Long update(Order order);
    
}
