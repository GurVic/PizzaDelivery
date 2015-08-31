/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.service;

//import java.time.DayOfWeek;
//import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.rd.domain.*;
import ua.epam.rd.repository.CustomerRepository;
import ua.epam.rd.repository.OrderRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class SimpleOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderCalculator calculator;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.getOrdersByCustomerId(customerId);
    }

    boolean isWorkingDay() {
//        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
//        return dayOfWeek != DayOfWeek.SUNDAY;
        return true;
    }

    @Override
    @Transactional
    public Long placeOrder(Order order) {
        if (!isWorkingDay()) {
            throw new IllegalStateException();
        }

        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return orderRepository.save(order);
    }
    
    @Override
    public boolean cancelOrder(Long orderId, String email) {
        Order order = orderRepository.getOrderById(orderId);
        //order.status != Done, Cancel
        //order.status = Cancel
        order.setOrderStatus(OrderStatus.CANCELED);
        orderRepository.update(order);
        return true;
    }

    @Override
    @Transactional
    public Order placeNewOrder(String name, Address address, Map<Pizza, Integer> pizzas) {
        Customer customer = customerRepository.findCustomerByName(name);
        Order newOrder = new Order();
        if (customer != null) {
            newOrder.setCustomer(customer);
            newOrder.setAddress(address);
            newOrder.setItems(pizzas);
            newOrder.setDate(new Date());

            Price orderPrice = calculator.calcPrice(pizzas, customer.getAccumulativeCard());
            newOrder.setPrice(orderPrice.getPrice());
            newOrder.setDiscount(orderPrice.getDiscount());
            // set order price sum to accumulative cart
            Double sum = customer.getAccumulativeCard().getSum();
            sum += orderPrice.getPrice() - orderPrice.getDiscount();
            customer.getAccumulativeCard().setSum(sum);

            orderRepository.save(newOrder);
        }
        return newOrder;
    }


}
