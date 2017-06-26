package demo.service;

import demo.domain.Order;
import demo.domain.OrderEvent;

import java.util.List;

public interface OrderService {
    List<Order> findOrdersByUserId(String userId);

    List<OrderEvent> findOrderEventsByOrderId(String orderId);

    Order getOrder(String orderId);

    void createOrder(Order order);

    void addOrderEvent(OrderEvent orderEvent, String orderId);
}
