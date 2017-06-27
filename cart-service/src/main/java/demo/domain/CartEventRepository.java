package demo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartEventRepository extends MongoRepository<CartEvent, String>{
    List<CartEvent> findCartEventsByUserIdOrderByDate(String userId);

    void deleteCartEventsByUserId(String userId);
}
