/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.service;

import ua.epam.rd.domain.Address;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Pizza;

import java.util.List;
import java.util.Map;

public interface OrderService {
    
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Long placeOrder(Order order);
    public boolean cancelOrder(Long orderId, String email);

    Order placeNewOrder(String name, Address address, Map<Pizza, Integer> pizzas);
    List<Order> getOrdersByCustomerId(Long customerId);
}
