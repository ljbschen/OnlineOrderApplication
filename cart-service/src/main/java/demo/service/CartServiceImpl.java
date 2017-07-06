package demo.service;

import demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private static final String PAYMENT_URL= "http://localhost:8080/payment-service/index";
    private static final String CREATE_ORDER_URL = "http://localhost:8080/order-service/orders";

    private CartEventRepository cartEventRepository;

    @Autowired
    public CartServiceImpl(CartEventRepository cartEventRepository) {
        this.cartEventRepository = cartEventRepository;
    }

    private Cart aggregation (String userId) {
        List<CartEvent> events = cartEventRepository.findCartEventsByUserIdOrderByDate(userId);
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemMap(new HashMap<>());
        for (CartEvent event : events) {
            CartItem item = event.getItem();
            if (event.getCartEventType().equals(CartEventType.ADD_ITEM)) {
                cart.getItemMap().put(item, cart.getItemMap().getOrDefault(item, 0) + 1);
            } else if (event.getCartEventType().equals(CartEventType.DELETE_ITEM)) {
                cart.getItemMap().put(item, cart.getItemMap().getOrDefault(item, 0) - 1);
            }
        }
        return cart;
    }

    @Override
    public List<CartItem> getCart(String userId) {
        Cart cart = aggregation(userId);
        List<CartItem> items = new ArrayList<>();
        for (CartItem item : cart.getItemMap().keySet()) {
            item.setItemQuantity(cart.getItemMap().get(item));
            items.add(item);
        }

        return items;
    }

    /*
    Send the order to order-service and returns the page for payment information
     */
    @Override
    public URI checkout(String userId, OrderNote orderNote) {
        Cart cart = aggregation(userId);
        System.out.println(cart);
        Order order = new Order();
        for (CartItem item : cart.getItemMap().keySet()) {
            item.setItemQuantity(cart.getItemMap().get(item));
            order.getItems().add(item);
        }
        order.setOrderNote(orderNote.getOrderNote());
        order.setShipping(orderNote.getShippingAddress());
        order.setUserId(userId);

        System.out.println(order);
        URI uri = null;

        try {
            // send the order to order-service
            RestTemplate restTemplate = new RestTemplate();
            System.out.println(CREATE_ORDER_URL);
            ResponseEntity<URI> response = restTemplate.postForEntity(CREATE_ORDER_URL, order, URI.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)) {
                uri = new URI(PAYMENT_URL);
                cartEventRepository.deleteCartEventsByUserId(userId);
            }
        } catch (URISyntaxException | HttpClientErrorException e) {
            e.printStackTrace();
        }
        return uri;
    }

    @Override
    public boolean addEvent(CartEvent cartEvent, String userId) {
        boolean result = false;
        if (cartEvent.getItem() == null || cartEvent.getCartEventType() == null) return false;
        try {
            if (cartEvent.getCartEventType() == CartEventType.CLEAR) {
                cartEventRepository.deleteCartEventsByUserId(userId);
            } else {
                cartEvent.setUserId(userId);
                cartEvent.setDate(new Date());
                cartEventRepository.save(cartEvent);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
