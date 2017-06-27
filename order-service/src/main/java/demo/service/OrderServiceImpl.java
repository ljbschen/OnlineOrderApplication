package demo.service;

import demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderEventRepository orderEventRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderEventRepository orderEventRepository) {
        this.orderEventRepository = orderEventRepository;
        this.orderRepository = orderRepository;
    }

    public List<Order> findOrdersByUserId(String userId) {
        return this.orderRepository.findByUserId(userId);
    }

    public List<OrderEvent> findOrderEventsByOrderId(String orderId) {
        return this.orderEventRepository.findOrderEventsByOrderId(orderId);
    }

    @Override
    public Order getOrder(String orderId) {
        return orderRepository.findOne(orderId);
    }

    @Override
    public void createOrder(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public void addOrderEvent(OrderEvent orderEvent, String orderId) {
        orderEvent.setOrderId(orderId);
        this.orderEventRepository.save(orderEvent);
    }

    public void process(OrderEvent orderEvent) {
        if (orderEvent.getType() == OrderEventType.CREATED) {

        }
    }
}
