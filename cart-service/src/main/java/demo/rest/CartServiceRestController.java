package demo.rest;

import demo.domain.CartEvent;
import demo.domain.CartItem;
import demo.domain.OrderNote;
import demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
public class CartServiceRestController {
    private CartService cartService;

    @Autowired
    public CartServiceRestController(CartService cartService) {
        this.cartService = cartService;
    }


    @RequestMapping(value = "/cart/{userId}/events", method = RequestMethod.POST)
    public boolean addEvent(@PathVariable String userId, @RequestBody CartEvent event) {
        return this.cartService.addEvent(event, userId);
    }

    @RequestMapping(value = "/cart/{userId}", method = RequestMethod.GET)
    public List<CartItem> getCart(@PathVariable String userId) {
        return this.cartService.getCart(userId);
    }

    @RequestMapping(value = "/cart/{userId}/checkout", method = RequestMethod.POST)
    public ResponseEntity<URI> checkout(@PathVariable String userId, @RequestBody OrderNote orderNote) {
        // return payment html
        URI uri = this.cartService.checkout(userId, orderNote);
        if (uri != null) return new ResponseEntity<>(uri, HttpStatus.ACCEPTED);
        else return new ResponseEntity<>((URI) null, HttpStatus.SERVICE_UNAVAILABLE);
    }

//    @Async
//    public Future<String> asyncMethodWithReturnType() {
//        System.out.println("Execute method asynchronously - "
//                + Thread.currentThread().getName());
//        try {
//            Thread.sleep(5000);
//            return new AsyncResult<String>("hello world !!!!");
//        } catch (InterruptedException e) {
//            //
//        }
//
//        return null;
//    }
}

