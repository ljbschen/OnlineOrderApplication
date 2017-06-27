package demo.service;

import demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
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

    public Cart getCart(String userId) {
        return aggregation(userId);
    }

    public URI checkout(String userId, OrderNote orderNote) {
        Cart cart = aggregation(userId);
        Order order = new Order();
        for (CartItem item : cart.getItemMap().keySet()) {
            item.setItemQuantity(cart.getItemMap().get(item));
            order.getItems().add(item);
        }
        order.setOrderNote(orderNote.getOrderNote());
        order.setUserId(userId);

        // send the order to order-service
        RestTemplate restTemplate = new RestTemplate();
        String paymentUrl = "";
        URI uri = restTemplate.postForObject(paymentUrl, order, URI.class);
        if (uri != null) cartEventRepository.deleteCartEventsByUserId(userId);
        return uri;
    }

    @Override
    public void addEvent(CartEvent cartEvent, String userId) {
        if (cartEvent.getCartEventType() == CartEventType.CLEAR) {
            cartEventRepository.deleteCartEventsByUserId(userId);
        } else {
            cartEvent.setUserId(userId);
            this.cartEventRepository.save(cartEvent);
        }
    }
}
