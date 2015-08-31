package ua.epam.rd.domain;

import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CalculatorTest {

	private static double DELTA = 0.003;

	@Mock
	private AccumulativeCard mockedCard;
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculatePriceWithoutAnyPizzaThrowException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		OrderCalculator calculator = new OrderCalculator();
		calculator.calcPrice(pizzas, mockedCard);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculatePriceWithToMachPizzasInOrderThrowException() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		for (int i = 1; i <= 11; i++) {
			pizzas.put(new Pizza("Test" + i, 10.0, PizzaType.Vegetarian), 1);
		}
		OrderCalculator calculator = new OrderCalculator();
		calculator.calcPrice(pizzas, mockedCard);
	}

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePriceWithNegativePizzasAmountThrowException() {
        Map<Pizza, Integer> pizzas = new HashMap<>();
        pizzas.put(new Pizza("Pizza1", 10.0, PizzaType.Sea), 2);
        pizzas.put(new Pizza("Pizza2", 20.0, PizzaType.Meat), -1);

        OrderCalculator calculator = new OrderCalculator();
        calculator.calcPrice(pizzas, mockedCard);
    }

	@Test
	public void testCalculatePriceWithOnePizza() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		double pizzaPrice = 10.0;
		pizzas.put(new Pizza("Test", pizzaPrice, PizzaType.Sea), 1);
		double expectedPrice = 10.0;
		
		OrderCalculator calculator = new OrderCalculator();
		Price price = calculator.calcPrice(pizzas, mockedCard);
		
		assertEquals(expectedPrice, price.getPrice(), DELTA);
	}
	
	@Test
	public void testCalculatePriceWith4Pizzas() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		double pizzaPrice = 10.0;
		for (int i = 1; i <= 4; i++) {
			pizzas.put(new Pizza("Test" + i, pizzaPrice, PizzaType.Meat), 1);
		}
		double expectedPrice = 40.0;
		
		OrderCalculator calculator = new OrderCalculator();
		Price price = calculator.calcPrice(pizzas, mockedCard);
		
		assertEquals(expectedPrice, price.getPrice(), DELTA);
	}
	
	@Test
	public void testCalculatePriceOrderedWithOneDiscountedPizzaNoDiscountCard() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		double pizzaPrice = 10.0;
		for (int i = 1; i <= 4; i++) {
			pizzas.put(new Pizza("Test" + i, pizzaPrice, PizzaType.Meat), 1);
		}
		pizzas.put(new Pizza("Test", 30.0, PizzaType.Sea), 1);
		double expectedPrice = 70;
		double expectedDiscount = 9;

		OrderCalculator calculator = new OrderCalculator();
		Price price = calculator.calcPrice(pizzas, mockedCard);

		assertEquals(expectedPrice, price.getPrice(), DELTA);
		assertEquals(expectedDiscount, price.getDiscount(), DELTA);
	}
	
	@Test
	public void testCalculatePriceWithOneDiscountAndDiscountCard() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		double pizzaPrice = 10.0;
		for (int i = 1; i <= 4; i++) {
			pizzas.put(new Pizza("Test" + i, pizzaPrice, PizzaType.Sea), 1);
		}
		pizzas.put(new Pizza("Test", 30.0, PizzaType.Sea), 1);
		double expectedPrice = 70.0;
		double expectedDiscount = 19.0;

		AccumulativeCard discountCard = mock(AccumulativeCard.class);
		when(discountCard.getSum()).thenReturn(100.0);

		OrderCalculator calculator = new OrderCalculator();
 		Price price = calculator.calcPrice(pizzas, discountCard);

		assertEquals(expectedPrice, price.getPrice(), DELTA);
		assertEquals(expectedDiscount, price.getDiscount(), DELTA);
	}
	
	@Test
	public void testCalculatePriceWithTenPizzasAndDiscountCard() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		double pizzaPrice = 10.0;
		for (int i = 1; i <= 10; i++) {
			pizzas.put(new Pizza("Test" + i, pizzaPrice, PizzaType.Sea), 1);
		}
		double expectedPrice = 100.0;
		double expectedDiscount = 13.0;

		AccumulativeCard discountCard = mock(AccumulativeCard.class);
		when(discountCard.getSum()).thenReturn(100.0);
		
		OrderCalculator calculator = new OrderCalculator();
		Price price = calculator.calcPrice(pizzas, discountCard);
		
		assertEquals(expectedPrice, price.getPrice(), DELTA);
		assertEquals(expectedDiscount, price.getDiscount(), DELTA);
	}
	
}
