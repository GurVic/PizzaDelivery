/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.rd.service.PizzaService;

//@Controller("helloController")
public class HelloSpringMVC {

    @Autowired
    private PizzaService pizzaService;
    
//    @RequestMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        return "Hello SpringMVC";
//    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String handleDefaultRequest(Model model) {
//        model.addAttribute("msg", new Date());
//        return "hello";
//    }
    
//    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
//    public String viewPizzas(Model model){
//        model.addAttribute("pizzas",
//                pizzaService.getAllPizzas());
//        return "pizzas";
//    }
}
