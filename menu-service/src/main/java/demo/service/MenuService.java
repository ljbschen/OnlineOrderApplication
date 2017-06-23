package demo.service;

import demo.domain.MenuItem;
import demo.domain.Request;
import demo.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;

public interface MenuService {

    boolean deleteAll(Request request);

    Page<MenuItem> findByItemPriceBetween(double low, double high, Pageable pageable);

    boolean addRestaurants (Request request);

    boolean addMenuItem(String restaurantName, Request request);

    Page<Restaurant> findRestaurantWithIn(String page, String size, String distance, Point point);

    Restaurant findByRestaurantName(String restaurantName);
}
