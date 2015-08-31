/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.domain;

import org.hibernate.annotations.NamedQuery;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Orders")
@Scope(value = "prototype")
@NamedQuery(name="getOrdersByCustomerId", query="SELECT o FROM Order o WHERE o.customer.id = :id")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "pizza_id")
    @Column(name = "items")
    private Map<Pizza, Integer> items = new HashMap<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private Double discount;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }    
    
    public Map<Pizza, Integer> getItems() {
        return items;
    }    
    
    public Order() {
        this.date = new Date();
    }

    public Order(Date date, Map<Pizza, Integer> items, Address address, OrderStatus orderStatus) {
        this.date = date;
        this.items = items;
        this.address = address;
        this.orderStatus = orderStatus;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setItems(Map<Pizza, Integer> items) {
        this.items = items;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (items != null ? !items.equals(order.items) : order.items != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        return orderStatus == order.orderStatus;

    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", customer=" + customer +
                ", items=" + items +
                ", address=" + address +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
