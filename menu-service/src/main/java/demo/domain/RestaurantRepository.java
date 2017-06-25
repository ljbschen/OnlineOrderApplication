package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, Long> {
    Restaurant findByRestaurantName(String name);

    Page<Restaurant> findAll(Pageable pageable);

    Page<Restaurant> findByLocationWithin(Circle c, Pageable pageable);

    void deleteRestaurantByRestaurantName(String name);
}
