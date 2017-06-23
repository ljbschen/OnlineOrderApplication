package demo.service;

import demo.domain.MenuItem;
import demo.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;

import java.util.List;

public interface MenuService {

    void deleteAll();

    Page<MenuItem> findByItemPriceBetween(double low, double high, Pageable pageable);

    void addRestaurants (List<Restaurant> restaurants);

    void addMenuItem(String restaurantName, List<MenuItem> menu);

    Page<Restaurant> findRestaurantWithIn(String page, String size, String distance, Point point);

    Restaurant findByRestaurantName(String restaurantName);
}
