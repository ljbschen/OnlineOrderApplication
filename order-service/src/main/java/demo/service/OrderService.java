package demo.service;

import demo.domain.Order;
import demo.domain.OrderEvent;

import java.net.URI;
import java.util.List;

public interface OrderService {
    List<Order> findOrdersByUserId(String userId);

    List<OrderEvent> findOrderEventsByOrderId(String orderId);

    Order getOrder(String orderId);

    boolean createOrder(Order order);

    boolean addOrderEvent(OrderEvent orderEvent);
}
