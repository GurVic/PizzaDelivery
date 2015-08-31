package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.OrderStatus;
import ua.epam.rd.service.OrderService;

import java.beans.PropertyEditorSupport;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderRESTController {

    @Autowired
    private OrderService orderService;

    // View order by id
    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> viewOrderById(@PathVariable(value = "id") Order order){
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    // Create order from jsonObject and return order
    @RequestMapping(value = "/items", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order, UriComponentsBuilder builder){
        System.out.println(order);
        orderService.placeOrder(order);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(builder.path("/pizza/{id}").buildAndExpand(pizza.getId()).toUri());
//        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    // View list of orders
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> viewOrders(){
        List<Order> list = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
    }

    // Delete order
    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Order order) {
//        pizzaService.
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Update order
    @RequestMapping(value = "/items", method = RequestMethod.PUT, headers = "Content-Type=application/json")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        System.out.println("____________ UPDATE ----------------------");
        System.out.println(order);
        System.out.println("____________ UPDATE ----------------------");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // return orders types
    @RequestMapping(value = "/items/types", method = RequestMethod.GET)
    public ResponseEntity<?> typesOrder() {
        OrderStatus[] arr = OrderStatus.values();
        return new ResponseEntity<>(arr, HttpStatus.OK);
    }

    private Order getOrderById(Long id) {
        if (id<=0) throw new IllegalArgumentException("ID<0");
        Order order = orderService.getOrderById(id);
        if (order == null) throw new NotFoundOrderException("Order id" + id + " not found" );
        return order;
    }

    @InitBinder
    private void orderBinder(WebDataBinder binder){
        binder.registerCustomEditor(Order.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String id) {
                        Order ord = null;
                        if (id != null && !id.trim().isEmpty()) {
                            Long pid = Long.valueOf(id);
                            ord = getOrderById(pid);
                        }
                        setValue(ord);
                    }
                }
        );
    }
}
