package demo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartEventRepository extends MongoRepository<CartEvent, String>{
    List<CartEvent> findCartEventsByUserIdOrderByDate(String userId);

    void deleteCartEventsByUserId(String userId);
}
