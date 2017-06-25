package demo.service;

import demo.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderEventRepository orderEventRepository;
    private OrderRepository orderRepository;

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
    public void createOrder(List<Item> items) {
        Date date = new Date();
        Order newOrder = new Order("admin", date, items, "address");
        this.orderRepository.save(newOrder);
    }
}
