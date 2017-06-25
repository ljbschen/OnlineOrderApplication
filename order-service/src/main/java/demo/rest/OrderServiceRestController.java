package demo.rest;

import demo.domain.Item;
import demo.domain.Order;
import demo.domain.OrderEvent;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderServiceRestController {
    private OrderService orderService;

    @Autowired
    public OrderServiceRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody List<Item> items) {
        orderService.createOrder(items);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{orderId}/events", method = RequestMethod.POST)
    public ResponseEntity<OrderEvent> addOrderEvent(@RequestBody OrderEvent orderEvent, @PathVariable String orderId) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
