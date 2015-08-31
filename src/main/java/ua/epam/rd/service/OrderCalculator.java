package ua.epam.rd.service;

import org.springframework.stereotype.Component;
import ua.epam.rd.domain.AccumulativeCard;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.Price;

import java.util.Map;
import java.util.Map.Entry;

@Component("orderCalculator")
public class OrderCalculator {
	private static final double DISCOUNT = 0.1;
    private static final double DISCOUNT4 = 0.3;

	public Price calcPrice(Map<Pizza, Integer> pizzas,	AccumulativeCard accumulativeCard) {
		int count = 0;
		double totalPrice = 0;
		double biggestPrice = 0;
        double discount = 0;
		Pizza pizza;
		if (pizzas != null) {
			for (Entry<Pizza, Integer> pizzaEntry: pizzas.entrySet()) {
				pizza = pizzaEntry.getKey();
                Integer num = pizzaEntry.getValue();
				if (num <= 0 || pizza == null || pizza.getPrice() <= 0) {
					throw new IllegalArgumentException();
				}
				count += num;
				totalPrice += pizza.getPrice() * num;
				if (biggestPrice < pizza.getPrice()) {
					biggestPrice = pizza.getPrice();
				}
			}
		}
        if (count <= 0 || count > 10) {
            throw new IllegalArgumentException();
        }
        if (count > 4) {
            // first discount
            discount += (biggestPrice)*DISCOUNT4;
        }

        // second discount
		if(accumulativeCard != null) {
			discount += accumulativeCard.getSum() * DISCOUNT;
		}
        return new Price(totalPrice, discount);
	}
}
