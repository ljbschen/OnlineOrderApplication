package demo.rest;

import demo.domain.Cart;
import demo.domain.Order;
import demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartServiceRestController {
    private CartService cartService;

    @Autowired
    public CartServiceRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = "/cart/{userId}/events", method = RequestMethod.POST)
    public void addEvent(@PathVariable String userId) {

    }

    @RequestMapping(value = "/cart/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        Cart cart = cartService.getCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    }

    @RequestMapping(value = "/cart/{userId}/checkout", method = RequestMethod.POST)
    public ResponseEntity<Order> checkout(@PathVariable String userId) {
        Order order = cartService.checkout(userId);
        return new ResponseEntity<>(order, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    }

}
