package ua.epam.rd.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;

@Controller("pizzaController")
@RequestMapping("/pizza")
@SessionAttributes("cart")
public class PizzaController extends AbstractPizzaContoller {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzas(Model model){
        if (!model.containsAttribute("cart")) {
            model.addAttribute("cart", userCart);
        }
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authroles", authentication.getAuthorities().toString());
        model.addAttribute("authname", authentication.getName());
        return "show";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newPizza(Model model){
        model.addAttribute("pizzatypes", PizzaType.values());
        return "addpizza";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String newPizzaAdd(@ModelAttribute Pizza newPizza) {
        pizzaService.addPizza(newPizza);
        return "redirect:";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPizza(@RequestParam("pizzaid") Pizza pizza,
                       Model model) {
        //Pizza pizza = getPizzaById(id);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("pizza", pizza);
        model.addAttribute("pizzatypes", PizzaType.values());
        return "editpizza";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPizzaPost(@ModelAttribute Pizza pizza) {
        pizzaService.updatePizza(pizza);
        return "redirect:";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String addPizzaToCart(@ModelAttribute("cart") Cart cart,
                                 @RequestParam("pizzaId") Pizza pizza,
                                 @RequestParam("amount") Integer amount) {
        if (amount > 0) {
            cart.putItem(pizza, amount);
        }
        return "redirect:";
    }
}
