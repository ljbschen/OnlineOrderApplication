package demo.rest;

import demo.domain.Order;
import demo.domain.OrderEvent;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderServiceRestController {
    private OrderService orderService;

    @Autowired
    public OrderServiceRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody Order order) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrder(@PathVariable String orderId) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/order/addevent", method = RequestMethod.POST)
    public ResponseEntity addOrderEvent(@RequestBody OrderEvent orderEvent) {
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
