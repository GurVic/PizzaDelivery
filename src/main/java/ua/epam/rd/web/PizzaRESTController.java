package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;
import ua.epam.rd.service.PizzaService;

import java.beans.PropertyEditorSupport;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaRESTController {

    @Autowired
    private PizzaService pizzaService;

//    @RequestMapping(method = RequestMethod.GET, value = "hello")
//    public ResponseEntity<String> hello() {
//        return new ResponseEntity<>("Hello from rest", HttpStatus.I_AM_A_TEAPOT);
//    }

    // View pizza by id
    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> viewPizzaById(@PathVariable(value = "id") Pizza pizza){
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }

    // Create pizza from jsonObject and return Uri to created pizza
    @RequestMapping(value = "/items", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public ResponseEntity<Pizza> createNewPizza(@RequestBody Pizza pizza, UriComponentsBuilder builder){
        System.out.println(pizza);
        pizzaService.addPizza(pizza);
        System.out.println(pizza);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(builder.path("/pizza/{id}").buildAndExpand(pizza.getId()).toUri());
//        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        return new ResponseEntity<>(pizza, HttpStatus.CREATED);
    }

    // View list of pizzas
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<List<Pizza>> viewPizzas(){
        List<Pizza> list = pizzaService.getAllPizzas();
        return new ResponseEntity<List<Pizza>>(list, HttpStatus.OK);
    }

    // Delete pizza
    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePizza(@PathVariable(value = "id") Pizza pizza) {
//        pizzaService.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Update pizza
    @RequestMapping(value = "/items", method = RequestMethod.PUT, headers = "Content-Type=application/json")
    public ResponseEntity<?> updatePizza(@RequestBody Pizza pizza) {
        pizzaService.addPizza(pizza);
        System.out.println("____________ UPDATE ----------------------");
        System.out.println(pizza);
        System.out.println("____________ UPDATE ----------------------");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // return pizzas types
    @RequestMapping(value = "/items/types", method = RequestMethod.GET)
    public ResponseEntity<?> typesPizza() {
        PizzaType[] arr = PizzaType.values();
        return new ResponseEntity<>(arr, HttpStatus.OK);
    }

    private Pizza getPizzaById(Long id) {
        if (id<=0) throw new IllegalArgumentException("ID<0");
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) throw new NotFoundPizzaException("Pizza id" + id + " not found" );
        return pizza;
    }

    @InitBinder
    private void pizzaBinder(WebDataBinder binder){
        binder.registerCustomEditor(Pizza.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String id) {
                        Pizza pizz = null;
                        if (id != null && !id.trim().isEmpty()) {
                            Long pid = Long.valueOf(id);
                            pizz = getPizzaById(pid);
                        }
                        setValue(pizz);
                    }
                }
        );
    }
}
