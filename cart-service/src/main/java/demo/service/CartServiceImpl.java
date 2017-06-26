package demo.service;

import demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        cart.setItems(new ArrayList<>());
        for (CartEvent event : events) {
            if (event.getCartEventType() == CartEventType.ADD_ITEM ||
                    event.getCartEventType() == CartEventType.DELETE_ITEM) {
                cart.getItems().add(null);
            }
        }
        return cart;
    }

    public Cart getCart(String userId) {
        return aggregation(userId);
    }

    public Order checkout(String userId) {
        Cart cart = aggregation(userId);
        Order order = new Order();
        order.setItems(cart.getItems());
        return order;
    }
}
