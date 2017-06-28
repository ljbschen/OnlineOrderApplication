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
        return this.orderEventRepository.findOrderEventsByOrderIdOrderByDateAsc(orderId);
    }

    @Override
    public Order getOrder(String orderId) {
        try {
            Order order = this.orderRepository.findOne(orderId);
            List<OrderEvent> events = this.orderEventRepository.findOrderEventsByOrderIdOrderByDateAsc(orderId);
            for (OrderEvent event : events) {
                order.process(event);
            }
            this.orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.orderRepository.findOne(orderId);
    }

    @Override
    public boolean createOrder(Order order) {
        boolean result = false;
        try {
            order.setOrderStatus(OrderStatus.PURCHASED);
            this.orderRepository.save(order);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addOrderEvent(OrderEvent orderEvent) {
        boolean result = false;
        try {
            orderEvent.setDate(new Date());
            this.orderEventRepository.save(orderEvent);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
