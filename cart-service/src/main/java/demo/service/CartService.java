package demo.service;

import demo.domain.Cart;
import demo.domain.Order;

public interface CartService {
    Cart getCart(String userId);

    Order checkout(String userId);
}
