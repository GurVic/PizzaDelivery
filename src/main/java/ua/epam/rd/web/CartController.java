package ua.epam.rd.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.epam.rd.domain.Pizza;

@Controller
@RequestMapping("/pizza/cart")
@SessionAttributes("cart")
public class CartController extends AbstractCartContoller {

    @Secured("ROLE_USER")
    @RequestMapping(method=RequestMethod.GET)
	public String viewCart(Model model) {
		if (!model.containsAttribute("cart")) {
			model.addAttribute("cart", userCart);
		}
		return "showcart";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editItemInCart(
			@ModelAttribute("cart") Cart cart,
			@RequestParam("pizzaId") Pizza pizza,
			@RequestParam("amount") Integer amount) {
		if (amount <= 0) {
			cart.removeItem(pizza);
		} else {
			cart.changeAmount(pizza, amount);
		}
		return "redirect:/profile/cart";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String removeItemFromCart(
			@ModelAttribute("cart") Cart cart,
			@RequestParam("pizzaId") Pizza pizza) {
		cart.removeItem(pizza);
		return "redirect:/profile/cart";
	}
}
