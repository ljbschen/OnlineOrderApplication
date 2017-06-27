package demo.rest;

import demo.domain.Order;
import demo.domain.OrderEvent;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody Order order) {
        boolean result = orderService.createOrder(order);
        if (result) return new ResponseEntity(HttpStatus.CREATED);
        else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/events", method = RequestMethod.POST)
    public ResponseEntity<OrderEvent> addOrderEvent(@RequestBody OrderEvent orderEvent) {
        boolean result = orderService.addOrderEvent(orderEvent);
        if (result) return new ResponseEntity<>(HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
