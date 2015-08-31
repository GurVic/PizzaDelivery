package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ua.epam.rd.domain.*;
import ua.epam.rd.service.CustomerService;
import ua.epam.rd.service.OrderCalculator;
import ua.epam.rd.service.OrderService;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	private Map<Pizza, Integer> cart;

	@Autowired
	private OrderCalculator costCalculator;

	public Cart() {
		cart = new HashMap<Pizza, Integer>();
	}
	
	public Map<Pizza, Integer> getCart() {
		return cart;
	}
	
	public void putItem(Pizza pizza, Integer amount) {
		cart.merge(pizza, amount, ((oldValue, newValue) -> oldValue + newValue));
	}
	
	public void removeItem(Pizza pizza) {
		cart.remove(pizza);
	}
	
	public void changeAmount(Pizza pizza, Integer amount) {
		cart.put(pizza, amount);
	}
	
	public Integer getItemsCount() {
		Integer count = 0;
		count += cart.values().stream().mapToInt(value -> value).sum();
		return count;
	}
	
	public Price getTotalSum() {
		if (cart.isEmpty()) {
			return new Price();
		}
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
		Customer customer = customerService.findCustomerByName(name);
		return costCalculator.calcPrice(cart, customer.getAccumulativeCard());
	}

    @Override
    public String toString() {
        return "Cart{" +
                "cart=" + cart +
                '}';
    }
}
	