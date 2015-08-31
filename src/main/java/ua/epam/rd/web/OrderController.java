package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import ua.epam.rd.domain.Address;
import ua.epam.rd.domain.Customer;
import ua.epam.rd.domain.Order;
import ua.epam.rd.service.CustomerService;
import ua.epam.rd.service.OrderService;

import java.util.List;

@Controller("orderController")
@RequestMapping("pizza/order")
@SessionAttributes("cart")
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Secured("ROLE_USER")
    @RequestMapping(value="/userorders", method= RequestMethod.GET)
    public String viewOrdersByCustomer(Model model) {
            Customer customer = customerService.findCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName());
            List<Order> orders = orderService.getOrdersByCustomerId(customer.getId());
            model.addAttribute("orders", orders);
            return "customerorders";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "showorders";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createNewOrder(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("authroles", authentication.getAuthorities().toString());
        model.addAttribute("authname", authentication.getName());

        return "createorder";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String placeNewOrder(@ModelAttribute Address address,
                                @ModelAttribute("cart") Cart cart) {
        System.out.println("adr: " + address);
        System.out.println("cart: " + cart);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authname: " + authentication.getName());
        orderService.placeNewOrder(authentication.getName(), address, cart.getCart());
        return "redirect:/jsp/pizza/";
    }

}
