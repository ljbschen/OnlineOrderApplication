package demo.service;

import demo.domain.Cart;
import demo.domain.CartEvent;
import demo.domain.OrderNote;

import java.net.URI;

public interface CartService {
    Cart getCart(String userId);

    URI checkout(String userId, OrderNote orderNote);

    boolean addEvent(CartEvent cartEvent, String userId);
}
