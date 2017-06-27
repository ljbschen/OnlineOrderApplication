package demo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderEventRepository extends MongoRepository<OrderEvent, String> {
    List<OrderEvent> findOrderEventsByOrderIdOrderByDateAsc(String orderId);
}
