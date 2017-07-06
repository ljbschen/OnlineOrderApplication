package demo.service;

import demo.domain.CartEvent;
import demo.domain.CartItem;
import demo.domain.OrderNote;

import java.net.URI;
import java.util.List;

public interface CartService {
    List<CartItem> getCart(String userId);

    URI checkout(String userId, OrderNote orderNote);

    boolean addEvent(CartEvent cartEvent, String userId);
}
